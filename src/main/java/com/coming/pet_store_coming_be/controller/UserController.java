package com.coming.pet_store_coming_be.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.security.TokenProvider;
import com.coming.pet_store_coming_be.service.UserService;
import com.coming.pet_store_coming_be.validation.UserValidationService;

import java.util.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  UserService userService;

  @Autowired
  UserValidationService userValidationService;

  @Autowired
  TokenProvider tokenProvider;

  @PostMapping("/auth/logout") // 로그아웃 API
  public ResponseEntity<Map<String, Object>> logutUser(@RequestHeader("Authorization") String token) throws SQLException {
    Map<String, Object> response = new HashMap<>();

    String userIdentifierId = tokenProvider.getUserIdentifierFromToken(token);

    userService.logout(userIdentifierId);

    response.put("status", HttpStatus.OK.value());
    response.put("success", true);
    response.put("message", "Logout successful.");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  // @PostMapping("/auth/refresh-token") // 사용자가 페이지 활동으로 인해 리프레시 토큰 만료 시간 업데이트
  // public ResponseEntity<Map<String, Object>> refreshToken(@RequestParam("token") String token) {
  //   Map<String, Object> response = new HashMap<>();

  //   try {

  //     String newAccessToken = tokenProvider.renewAccessToken(token); // 새로운 액세스 토큰 발급

  //     // 리프레시 토큰 테이블 업데이트

  //     response.put("status", HttpStatus.OK.value());
  //     response.put("success", true);
  //     response.put("message", "Token refreshed successfully.");
  //     response.put("refreshToken", newAccessToken);

  //     return new ResponseEntity<>(response, HttpStatus.OK);

  //   } catch (Exception e) {

  //     // 새로운 액세스 토큰 발급 실패
  //     response.put("status", HttpStatus.UNAUTHORIZED.value());
  //     response.put("success", false);
  //     response.put("message", "Failed to refresh token. Please log in again.");

  //     return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  //   }
  // }
  
  @GetMapping("/protected-resource") // 토큰 만료 여부 확인 Controller 로직
  public ResponseEntity<Map<String, Object>> getProtectedResource(@RequestHeader("Authorization") String token) {
    Map<String, Object> response = new HashMap<>();

    try {
      // 토큰이 만료된 경우
      if(tokenProvider.isTokenExpired(token)) {
        response.put("status", HttpStatus.UNAUTHORIZED.value());
        response.put("success", false);
        response.put("message", "Access token is expired. Please refresh the token.");

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
      }

      // 토큰 시간이 남아있을 경우
      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("message", "Protected resource accessed successfully.");

      return new ResponseEntity<>(response, HttpStatus.OK);

    } catch (Exception e) {
      // 잘못된 토큰일 경우
      response.put("status", HttpStatus.UNAUTHORIZED.value());
      response.put("success", false);
      response.put("message", "Invalid token.");

      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
  }
  

}
