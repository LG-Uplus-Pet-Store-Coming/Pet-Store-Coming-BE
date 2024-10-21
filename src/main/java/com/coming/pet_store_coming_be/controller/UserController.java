package com.coming.pet_store_coming_be.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {
  
  @PostMapping("/sign-up")
  public String signUpUser(@RequestBody String entity) {
      return entity;
  }

}
