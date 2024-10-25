package com.coming.pet_store_coming_be.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
  
  @PostMapping("/social/kakao")
  public String getKakaoAccessToken(@RequestBody Map<String, Object> request) {
    System.out.println(request);
    
    return "Hello";
  }

}
