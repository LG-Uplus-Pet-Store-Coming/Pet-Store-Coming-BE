package com.coming.pet_store_coming_be.controller.auth;

import java.util.Map;
import java.util.HashMap;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.service.auth.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
public class AuthGetContoller {
  
  @Autowired
  AuthService authService;

  @GetMapping("/sign-in")
  public ResponseEntity<Map<String, Object>> getLoginUser(@RequestParam("email") String email, @RequestParam("email") String password) throws SQLException {
    Map<String, Object>  response = new HashMap<>();
    
    // 입력으로 주어진 email를 통해 DB에서 해당 데이터를 사용자 정보를 가져온다.
    UserDTO userInfo = authService.getUserEmailMath(email);

    // 1. email에 해당하는 사용자 정보 데이터가 없을 경우
    if(userInfo == null) {
      response.put("status", HttpStatus.NOT_FOUND.value());
      response.put("success", false);
      response.put("message", "User wuth the provided email address could not be found.");
      response.put("errorCode", "USER_NOT_FOUND");

      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 2. email에 해당하는 사용자 정보 데이터를 가져왔을 경우 - 비밀번호 일치 여부 확인
    else if(!authService.isPasswordMath(password, userInfo.getPassword())) {
      response.put("status", HttpStatus.UNAUTHORIZED.value());
      response.put("success", false);
      response.put("message", "The password provided is incorrect.");
      response.put("errorCode", "INVALID_PASSWORD");

      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // 3. 중복 로그인을 시도할 경우
    else if(userInfo.getIsActive()) {
      response.put("status", HttpStatus.CONFLICT.value());
      response.put("success", false);
      response.put("message", "You are already logged in on another device.");
      response.put("errorCode", "DUPLICATE_LOGIN_ATTEMPT");

      return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }



    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  

}
