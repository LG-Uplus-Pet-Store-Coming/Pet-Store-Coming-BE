package com.coming.pet_store_coming_be.service;

import com.coming.pet_store_coming_be.dto.UserDTO;

public interface UserService {

  // 회원가입 서비스
  public boolean signUpUser(UserDTO user);  
  
}
