package com.coming.pet_store_coming_be.service;

import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.UserDTO;

public interface UserService {
  public boolean signUpUser(UserDTO user) throws SQLException; // 회원가입 서비스
  public boolean emailCheck(String email) throws SQLException;
}
