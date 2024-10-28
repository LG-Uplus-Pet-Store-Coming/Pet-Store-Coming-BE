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
  
  @PostMapping("/logout")
  public String postUserLogout(@RequestHeader("Authorization") String token) throws SQLException {
    authService.logoutUser(token, tokenProvider.getUserIdFromToken(token));

    return "entity";
  }
  


}
