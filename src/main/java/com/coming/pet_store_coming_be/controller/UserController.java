package com.coming.pet_store_coming_be.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.service.UserService;
import com.coming.pet_store_coming_be.validation.UserValidationService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  UserService userService;

  @Autowired
  UserValidationService userValidationService;

  @PostMapping("/sign-up") // 회원가입 Contoller
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

  @GetMapping("/auth/login") // 로그인 Contoller
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

    // JWT 생성
    String token = "";

    response.put("status", HttpStatus.OK.value());
    response.put("success", true);
    response.put("message", "Login successful.");
    response.put("token", token);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  

}
