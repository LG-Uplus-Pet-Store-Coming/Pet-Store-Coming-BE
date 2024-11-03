package com.coming.pet_store_coming_be.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth/social")
public class AuthSocialController {
  
  @GetMapping("/kakako")
  public String getKakaoAccessToken(@RequestBody String entity) {
      //TODO: process POST request
      
      return entity;
  }
  

}
