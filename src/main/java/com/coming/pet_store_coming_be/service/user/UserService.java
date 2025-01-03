package com.coming.pet_store_coming_be.service.user;

import java.util.Map;
import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.UserDTO;

public interface UserService {

  public Map<String, Object> getFindUserEmailService(String email, String name, String phoneNumber) throws SQLException; // 아이디 찾기 비즈니스 로직 인스턴스 메서드
  public void updateUserPassswordService(Map<String, String> params) throws SQLException; // 비밀번호 재설정 비즈니스 로직 인스턴스 메서드
  public void updateUserInfoService(UserDTO user) throws SQLException; // 유저 회원정보 수정 비즈니스 로직 인스턴스 메서드
  
}
