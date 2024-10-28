package com.coming.pet_store_coming_be.service;

import java.util.Date;
import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.UserDTO;

public interface UserService {
  public boolean signUpUser(UserDTO user) throws SQLException; // 회원가입 서비스
  public UserDTO emailCheck(String email) throws SQLException; // Email을 통해 UserDTO 데이터 전달
  public void refreshTokenAndExpiry(String id, String refreshToken, Date tokenExpiry, boolean isActive) throws SQLException; // 리프레시 토큰, 토큰 만료 시간 업데이트
  public void logout(String userIdentifierId) throws SQLException; // 로그아웃 서비스
}
