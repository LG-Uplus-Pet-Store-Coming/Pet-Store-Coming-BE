package com.coming.pet_store_coming_be.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.config.JwtProperties;
import com.coming.pet_store_coming_be.dto.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;


@Service
public class TokenProvider {
  
  @Autowired
  private JwtProperties jwtProperties;

  // 토큰 생성 로직
  public String createToken(UserDTO user) {
    Map<String, Object> claims = new HashMap<>();

    // JWT payload에 포함될 정보 설정 (password, refresh_token, token_expiry 제외하고 설정)
    claims.put("userIdentifierId", user.getUserIdentifierId());
    claims.put("email", user.getEmail());
    claims.put("name", user.getName());
    claims.put("address", user.getAddress());
    claims.put("profileImageUrl", user.getProfileImageUrl());
    claims.put("profileImageAlt", user.getProfileImageAlt());
    claims.put("isActive", user.getIsActive());
    claims.put("role", user.getRole());

    byte[] keyBytes = jwtProperties.getSecretKey().getBytes(); // 비밀키를 바이트 배열로 반환
    Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName()); // Key 객체 생성

    // 생성한 토큰 반환
    return Jwts.builder()
      .setClaims(claims) // 클레임 설정
      .setSubject(user.getUserIdentifierId()) // 사용자 식별자 설정
      .setIssuedAt(new Date()) // 토큰 발행 시간 설정
      .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime())) // 만료 시간 설정
      .signWith(secretKey, SignatureAlgorithm.HS512)
      .compact(); // 토큰 생성
  }

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

  // 토큰 만료 시간 설정 로직
  public Date setTokenExpiry() {
    return new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime());
  }

}
