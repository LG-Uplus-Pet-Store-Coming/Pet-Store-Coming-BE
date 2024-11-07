package com.coming.pet_store_coming_be.service.user;

import java.sql.SQLException;

public interface UserService {

  public String getFindUserEmailService(String name, String phoneNumber) throws SQLException; // 아이디 찾기 비즈니스 로직 인스턴스 메서드
  
}
