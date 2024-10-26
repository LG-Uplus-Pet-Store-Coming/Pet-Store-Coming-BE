package com.coming.pet_store_coming_be.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.config.JwtProperties;
import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.validation.UserValidationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;


@Service
public class TokenProvider {
  
  @Autowired
  private JwtProperties jwtProperties;

  @Autowired
  UserValidationService userValidationService;

  // 토큰 생성 로직
  // public String createToken(UserDTO user) {
  //   Map<String, Object> claims = new HashMap<>();

  //   // JWT payload에 포함될 정보 설정 (password, refresh_token, token_expiry 제외하고 설정)
  //   claims.put("userIdentifierId", user.getUserIdentifierId());
  //   claims.put("email", user.getEmail());
  //   claims.put("name", user.getName());
  //   claims.put("address", user.getAddress());
  //   claims.put("profileImageUrl", user.getProfileImageUrl());
  //   claims.put("profileImageAlt", user.getProfileImageAlt());
  //   claims.put("isActive", user.getIsActive());
  //   claims.put("role", user.getRole());

  //   byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
  //   Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

  //   // 생성한 토큰 반환
  //   return Jwts.builder()
  //     .setClaims(claims) // 클레임 설정
  //     .setSubject(user.getUserIdentifierId()) // 사용자 식별자 설정
  //     .setIssuedAt(new Date()) // 토큰 발행 시간 설정
  //     .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime())) // 만료 시간 설정
  //     .signWith(secretKey, SignatureAlgorithm.HS512)
  //     .compact(); // 토큰 생성
  // }

  // 리프레시 토큰 생성 로직
  public String createRefreshToken(String userIdentifierId) {
    byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
    Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

    return Jwts.builder()
      .setSubject(userIdentifierId) // 사용자 식별자 설정
      .setIssuedAt(new Date()) // 토큰 발행 시간 설정
      .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime())) // 만료 시간 설정
      .signWith(secretKey, SignatureAlgorithm.HS512)
      .compact(); // 리플레시 토큰 생성
  }

  // 토큰 만료 시간 확인 로직
  public boolean isTokenExpired(String token) {
    byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
    Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

    Claims claims = Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody();

    // Date exprirationDate = claims.getExpiration();

    // return exprirationDate.before(exprirationDate);

    Date expirationDate = claims.getExpiration(); // 현재 토큰의 만료 시간

    return expirationDate.before(new Date()); // 현재 시간과 비교하여 만료 여부 확인
  }

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

  // JWT 토큰의 subject 부분에서 사용자 식별자 추출 로직
  public String getUserIdentifierFromToken(String token) {
    Claims claims = Jwts.parserBuilder()
      .setSigningKey(jwtProperties.getSecretKey().getBytes())
      .build()
      .parseClaimsJws(token)
      .getBody();

    return claims.getSubject();
  }

  // 토큰 만료 시간 설정 로직
  public Date setTokenExpiry() {
    return new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime());
  }

}
