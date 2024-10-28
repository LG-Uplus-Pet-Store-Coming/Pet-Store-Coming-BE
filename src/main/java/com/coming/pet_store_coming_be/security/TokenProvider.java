package com.coming.pet_store_coming_be.security;

import java.util.Set;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.config.JwtProperties;
import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.validation.UserValidationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;


@Service
public class TokenProvider {
  
  @Autowired
  private JwtProperties jwtProperties;

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Autowired
  UserValidationService userValidationService;

  // 토큰 생성 로직
  public String createToken(UserDTO user) {
    Map<String, Object> claims = new HashMap<>();

    // JWT payload에 포함될 정보 설정 (password, refresh_token 제외하고 설정)
    claims.put("userId", user.getId());
    claims.put("email", user.getEmail());
    claims.put("name", user.getName());
    claims.put("address", user.getAddress());
    claims.put("isActive", user.getIsActive());
    claims.put("role", user.getRole());

    byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
    Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

    // 생성한 토큰 반환
    return Jwts.builder()
      .setClaims(claims) // 클레임 설정
      .setSubject(user.getId()) // 사용자 식별자 설정
      .setIssuedAt(new Date()) // 토큰 발행 시간 설정
      .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime())) // 만료 시간 설정
      .signWith(secretKey, SignatureAlgorithm.HS512)
      .compact(); // 토큰 생성
  }

  // 리프레시 토큰 생성 로직
  public String createRefreshToken(String userId, String deviceId) {
    byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
    Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

    String refreshToken = Jwts.builder()
      .setSubject(userId) // 사용자 식별자 설정
      .claim("deviceId", deviceId)
      .setIssuedAt(new Date()) // 토큰 발행 시간 설정
      .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime())) // 만료 시간 설정
      .signWith(secretKey, SignatureAlgorithm.HS512)
      .compact(); // 리플레시 토큰 생성

    // Redis에 디바이스별로 Refresh Token 저장
    String redisKey = "refreshToken:" + userId + ":" + deviceId;
    redisTemplate.opsForValue().set(redisKey, refreshToken, jwtProperties.getRefreshExpirationTime(), TimeUnit.MILLISECONDS);

    return refreshToken; // 리프레시 토큰 반환
  }

  // 기존 디바이스 토큰 무효화
  public void invalidatePreviousTokens(String userId, String currentDeviceId) {
    Set<String> keys = redisTemplate.keys("refreshToken:" + userId + ":*");

    if(keys != null) {
      for(String key : keys) {
        if(!key.endsWith(":" + currentDeviceId)) {
          redisTemplate.delete(keys); // 현재 디바이스 외의 모든 토큰 삭제
        }
      }
    }
  }

  // Redis에 디바이스별 Refresh Token 저장
  public void saveRefreshToken(String userId, String refreshToken, String deviceId) {
    String key = "refreshToken:" + userId + ":" + deviceId;
    redisTemplate.opsForValue().set(key, refreshToken, jwtProperties.getRefreshExpirationTime(), TimeUnit.MILLISECONDS);
  }

  // JWT 토큰의 subject 부분에서 사용자 식별자 추출 로직
  public String getUserIdFromToken(String token) {
    byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
    Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

    // 예외 처리를 통해 토큰 만료된 토큰을 처리
    try {
      Claims claims = Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody();

      return claims.getSubject();
    } catch (ExpiredJwtException e) {
      // 만료된 토큰의 경우 예외에서 Claims를 가져옴
      return e.getClaims().getSubject();
    }

  }

  // 토큰 블래리스트에 추가하여 무효화
  public void invalidateToken(String token) {
    byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
    Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

    try {
      // 토큰에서 만료 시간을 추출
      Claims claims = Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody();

      Date expirationDate = claims.getExpiration();
      long expirationMillis = expirationDate.getTime() - System.currentTimeMillis();

      // 만료 시간만큼 Redis에 저장하여 블랙리스트에 등록
      ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
      valueOperations.set(token, "invalid", expirationMillis, TimeUnit.MILLISECONDS);
    } catch (ExpiredJwtException e) {
      // 만료된 토큰의 경우 Claims를 예외에서 가져옴
      Claims claims = e.getClaims();
      Date expirationDate = claims.getExpiration();
      long expirationMillis = expirationDate.getTime() - System.currentTimeMillis();

      // 만료된 토큰도 블랙리스트에 등록 (남은 시간 동안 유지)
      ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
      valueOperations.set(token, "invalid", expirationMillis, TimeUnit.MILLISECONDS);
    }
  }

  // 토큰이 블랙리스트에 등록되어 무효화되었는지 확인
  public boolean isTokenInvalid(String token) {
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    return valueOperations.get(token) != null;
  }

  // 토큰 만료 시간 설정 로직
  public Date setTokenExpiry() {
    return new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime());
  }


  // // 토큰 만료 시간 확인 로직
  // public boolean isTokenExpired(String token) {
  //   byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
  //   Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

  //   // try

  //   Claims claims = Jwts.parserBuilder()
  //     .setSigningKey(secretKey)
  //     .build()
  //     .parseClaimsJws(token)
  //     .getBody();

  //   // Date exprirationDate = claims.getExpiration();

  //   // return exprirationDate.before(exprirationDate);

  //   Date expirationDate = claims.getExpiration(); // 현재 토큰의 만료 시간

  //   return expirationDate.before(new Date()); // 현재 시간과 비교하여 만료 여부 확인
  // }

  // // Access Token 갱신 로직
  // public String renewAccessToken(String refreshToken) throws Exception {
  //   byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
  //   Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

  //   // refreshToken 유효성 검증
  //   Jwts.parserBuilder()
  //     .setSigningKey(secretKey)
  //     .build()
  //     .parseClaimsJws(refreshToken);
    
  //   String userIdentifier = Jwts.parserBuilder()
  //     .setSigningKey(secretKey)
  //     .build()
  //     .parseClaimsJws(refreshToken)
  //     .getBody()
  //     .getSubject();

  //   UserDTO user = userValidationService.isUserIdentifierMath(userIdentifier);

  //   return createToken(user);
  // }

}
