package com.coming.pet_store_coming_be.service.user;

import java.util.Map;
import java.sql.SQLException;
import java.util.HashMap;

import com.coming.pet_store_coming_be.dao.user.UserDAO;
import com.coming.pet_store_coming_be.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDAO dao;

  @Override
  public Map<String, Object> getFindUserEmailService(String name, String phoneNumber) throws SQLException {
    Map<String, Object> params = new HashMap<>();

    params.put("name", name);
    params.put("phoneNumber", phoneNumber);
   
    return dao.getFindUserEmail(params);
  }

  @Override // 비밀번호 재설정 비즈니스 로직 인스턴스 메서드
  public void updateUserPassswordService(Map<String, String> params) throws SQLException {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    params.put("newPassword", bCryptPasswordEncoder.encode(params.get("newPassword"))); // 암호화 시킨 새로운 비밀번호 수정

    dao.updateUserPassword(params);
  }

  @Override // 유저 회원정보 수정 비즈니스 로직
  public void updateUserInfoService(UserDTO user) throws SQLException {
    dao.updateUserInfo(user);
  }
  
}
