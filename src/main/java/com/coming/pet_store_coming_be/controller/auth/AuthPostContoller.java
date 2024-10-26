package com.coming.pet_store_coming_be.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.service.auth.AuthService;

import java.sql.SQLException;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/*
 * Description: Auth 관련 POST HTTP Method를 포함하는 Controller
 */

@RestController
@RequestMapping("/auth")
public class AuthPostContoller {
  
  @Autowired
  AuthService authService;

  @PostMapping("/sign-up") // 회원가입 API 설계
  public ResponseEntity<Map<String, Object>> postMethodName(@RequestBody UserDTO user) throws SQLException {
    Map<String, Object> response = new HashMap<>();
    
    // 1. 이메일 중복 확인
    if(authService.isUserEmailMath(user.getEmail())) {
      response.put("status", HttpStatus.CONFLICT.value());
      response.put("success", false);
      response.put("errorCode", "DUPLICATE_EMAIL");
      response.put("message", "Duplicate User Email. Please choose a different one.");

      return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // 2. 아이디가 중복되지 않았을 경우 회원가입 진행

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  

}
