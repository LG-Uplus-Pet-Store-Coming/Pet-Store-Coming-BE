package com.coming.pet_store_coming_be.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.service.UserService;
import com.coming.pet_store_coming_be.validation.UserValidationService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  UserService userService;

  @Autowired
  UserValidationService userValidationService;

  @PostMapping("/sign-up")
  public ResponseEntity<Map<String, Object>> signUpUser(@RequestBody UserDTO user) throws SQLException { // 회원가입 Contoller

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

}
