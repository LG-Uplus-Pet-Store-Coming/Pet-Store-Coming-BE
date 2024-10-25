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

  @PostMapping("/sign-up") // 회원가입 API
  public ResponseEntity<Map<String, Object>> signUpUser(@RequestBody UserDTO user) throws SQLException {

    Map<String, Object> response = new HashMap<>();

    // 1. 아이디 중복 확인
    if(userValidationService.isUserEmailAvailalbe(user.getEmail())) {
      response.put("status", HttpStatus.CONFLICT.value());
      response.put("success", false);
      response.put("errorCode", "DUPLICATE_EMAIL");
      response.put("message", "Duplicate User Email. Please choose a different one.");

      return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // 2. 아이디가 중복되지 않았을 경우 회원가입 진행
    if(userService.signUpUser(user)) {
      // 회원가입 성공
      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("message", "Signup completed successfully.");

      return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 회원가입 실패
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("success", false);
    response.put("message", "The signup request is invalid. Please check the input values.");
    response.put("errorCode", "INVALID_SIGNUP_REQUEST");

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/auth/login") // 로그인 API
  public ResponseEntity<Map<String, Object>> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) throws SQLException {
    
    // 입력으로 주어진 email 파라미터를 통해 DB에서 해당 데이터를 가져온다.
    UserDTO userInfo = userService.emailCheck(email);
    Map<String, Object> response = new HashMap<>();

    if(userInfo == null) { // 데이터를 가져오지 못했을 경우
      response.put("status", HttpStatus.NOT_FOUND.value());
      response.put("success", false);
      response.put("message", "User with the provided email address could not be found.");
      response.put("errorCode", "USER_NOT_FOUND");
      
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    } 
    
    else if(!userValidationService.isPasswordMath(password, userInfo.getPassword())) { // 비밀번호가 일치하지 않을 경우
      response.put("status", HttpStatus.UNAUTHORIZED.value()); // 401 상태 코드
      response.put("success", false);
      response.put("message", "The password provided is incorrect.");
      response.put("errorCode", "INVALID_PASSWORD");
      
      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    else if(userInfo.getIsActive()) { // 중복 로그인을 시도할 경우
      response.put("status", HttpStatus.CONFLICT.value());  // 409 상태 코드
      response.put("success", false);
      response.put("message", "You are already logged in on another device.");
      response.put("errorCode", "DUPLICATE_LOGIN_ATTEMPT");

      return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    String token = tokenProvider.createToken(userInfo); // JWT 토큰 생성
    String refreshToken = tokenProvider.createRefreshToken(userInfo.getUserIdentifierId()); // 리프레시 토큰 생성
    Date tokenExpiry = tokenProvider.setTokenExpiry(); // 토큰 만료 시간 설정

    // 로그인 한 사용자에게 refresh token과 token expiry 기간 설정
    userInfo.setRefreshToken(refreshToken);
    userInfo.setTokenExpiry(tokenExpiry);
    userInfo.setIsActive(true);

    // 로그인 사용자 토큰 정보 업데이트
    userService.refreshTokenAndExpiry(userInfo.getUserIdentifierId(), refreshToken, tokenExpiry, userInfo.getIsActive());

    response.put("status", HttpStatus.OK.value());
    response.put("success", true);
    response.put("message", "Login successful.");
    response.put("token", token);
    response.put("refreshToken", tokenExpiry);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
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
  
  @PostMapping("/auth/refresh-token") // 사용자가 페이지 활동으로 인해 리프레시 토큰 만료 시간 업데이트
  public ResponseEntity<Map<String, Object>> refreshToken(@RequestParam("token") String token) {
    Map<String, Object> response = new HashMap<>();

    try {

      String newAccessToken = tokenProvider.renewAccessToken(token); // 새로운 액세스 토큰 발급

      // 리프레시 토큰 테이블 업데이트

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("message", "Token refreshed successfully.");
      response.put("refreshToken", newAccessToken);

      return new ResponseEntity<>(response, HttpStatus.OK);

    } catch (Exception e) {

      // 새로운 액세스 토큰 발급 실패
      response.put("status", HttpStatus.UNAUTHORIZED.value());
      response.put("success", false);
      response.put("message", "Failed to refresh token. Please log in again.");

      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
  }
  
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
