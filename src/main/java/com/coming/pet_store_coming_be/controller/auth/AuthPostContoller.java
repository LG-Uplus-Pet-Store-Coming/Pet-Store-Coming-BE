package com.coming.pet_store_coming_be.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.security.TokenProvider;
import com.coming.pet_store_coming_be.service.auth.AuthService;

import java.sql.SQLException;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


/*
 * Description: Auth 관련 POST HTTP Method를 포함하는 Controller
 */

@RestController
@RequestMapping("/auth")
public class AuthPostContoller {
  
  @Autowired
  AuthService authService;

  @Autowired
  TokenProvider tokenProvider;

  @PostMapping("/sign-in") // 로그인 설계
  public ResponseEntity<Map<String, Object>> getLoginUser(@RequestBody Map<String, String> request) throws SQLException {
    Map<String, Object>  response = new HashMap<>();


    try {
      // 입력으로 주어진 email를 통해 DB에서 해당 데이터를 사용자 정보를 가져온다.
      UserDTO userInfo = authService.getUserEmailMath(request.get("email"));

      // 1. email에 해당하는 사용자 정보 데이터가 없을 경우
      if(userInfo == null) {
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("success", false);
        response.put("message", "User wuth the provided email address could not be found.");
        response.put("errorCode", "USER_NOT_FOUND");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      }
      
      // 2. email에 해당하는 사용자 정보 데이터를 가져왔을 경우 - 비밀번호 일치 여부 확인
      else if(!authService.isPasswordMath(request.get("password"), userInfo.getPassword())) {
        response.put("status", HttpStatus.UNAUTHORIZED.value());
        response.put("success", false);
        response.put("message", "The password provided is incorrect.");
        response.put("errorCode", "INVALID_PASSWORD");

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
      }

      // 3. 모든 조건이 일치하지 않을 경우
      String token = tokenProvider.createToken(userInfo); // 토큰 생성
      String refreshToken = tokenProvider.createRefreshToken(userInfo.getId(), request.get("deviceId"));
      
      authService.invalidateAndSaveNewRefreshToken(userInfo.getId(), refreshToken, request.get("deviceId")); // 기존 토큰 무효화 및 새로운 디바이스 리프레시 토큰 저장

      // 토큰 생성 및 UserInfo refresh_token 및 is_active 갱신을 완료한 이후 응답 생성
      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("message", "Login successful.");
      response.put("token", token);
      response.put("refreshToken", refreshToken);
      response.put("expirationTime", tokenProvider.setTokenExpiry().getTime()); // 토큰 만료 시간 추가

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to search products.");
      response.put("errorCode", "PRODUCT_SEARCH_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // // 3. 중복 로그인을 시도할 경우
    // else if(userInfo.getIsActive()) {
    //   response.put("status", HttpStatus.CONFLICT.value());
    //   response.put("success", false);
    //   response.put("message", "You are already logged in on another device.");
    //   response.put("errorCode", "DUPLICATE_LOGIN_ATTEMPT");

    //   return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    // }

    

    // // 로그인 성공 시 refresh_token 및 is_active 갱신
    // userInfo.setRefreshToken(tokenProvider.createRefreshToken(userInfo.getId()));
    // userInfo.setIsActive(true);

    // authService.refreshTokenAndExpiry(userInfo.getId(), userInfo.getRefreshToken(), userInfo.getIsActive());
  }

  @PostMapping("/sign-up") // 회원가입 API 설계
  public ResponseEntity<Map<String, Object>> postCreateAccount(@RequestBody UserDTO user) throws SQLException {
    Map<String, Object> response = new HashMap<>();
    
    // 1. 이메일 중복 확인
    if(authService.isUserEmailMath(user.getEmail())) {
      response.put("status", HttpStatus.CONFLICT.value());
      response.put("success", false);
      response.put("errorCode", "DUPLICATE_EMAIL");
      response.put("message", "Duplicate User Email. Please choose a different one.");

      return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // 2. 입력값이 잘못되었을 경우
    if(!authService.signUpUser(user)) {
      response.put("status", HttpStatus.BAD_REQUEST.value());
      response.put("success", false);
      response.put("message", "The signup request is invalid. Please check the input values.");
      response.put("errorCode", "INVALID_SIGNUP_REQUEST");

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);  
    }

    // 3. 회원가입을 성공한 경우
    response.put("status", HttpStatus.OK.value());
    response.put("success", true);
    response.put("message", "Signup completed successfully.");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  @PostMapping("/logout") // 로그아웃 API
  public ResponseEntity<Map<String, Object>> postUserLogout(@RequestHeader("Authorization") String token) throws SQLException {
    Map<String, Object> response = new HashMap<>();
    
    // 1. 토큰이 이미 블랙리스트에 있을 경우 -> 로그아웃 요청이 여러번 올 경우
    if(tokenProvider.isTokenInvalid(token)) {
      response.put("status", HttpStatus.CONFLICT.value());
      response.put("success", false);
      response.put("message", "Token is already invalidated. User is already logged out.");
      response.put("errorCode", "TOKEN_ALREADY_INVALIDATED");

      return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    try {
      authService.logoutUser(token, tokenProvider.getUserIdFromToken(token));
    } catch (Exception e) {
      // 2. 유효하지 않은 토큰일 경우
      response.put("status", HttpStatus.UNAUTHORIZED.value());
      response.put("success", false);
      response.put("message", "Invalid token. User is not authorized.");
      response.put("errorCode", "INVALID_TOKEN");

      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // 3. 로그아웃 성공 응답
    response.put("status", HttpStatus.OK.value());
    response.put("success", true);
    response.put("message", "Logged out successfully.");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  


}
