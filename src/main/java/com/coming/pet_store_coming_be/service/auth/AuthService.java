package com.coming.pet_store_coming_be.service.auth;

import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.UserDTO;

public interface AuthService {

  public boolean isUserEmailMath(String email) throws SQLException; // 이메일 중복 확인 비즈니스 로직 인터페이스 메서드
  public boolean signUpUser(UserDTO user) throws SQLException; // 회원가입 비즈니스 로직 인터페이스 메서드

}
