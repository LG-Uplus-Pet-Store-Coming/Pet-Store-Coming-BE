package com.coming.pet_store_coming_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.UserDAO;
import com.coming.pet_store_coming_be.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDAO dao;

  @Override // 회원가입 Service
  public boolean signUpUser(UserDTO user) {
    return false;
  }

  
  
}