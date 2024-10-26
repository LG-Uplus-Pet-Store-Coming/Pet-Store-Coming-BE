package com.coming.pet_store_coming_be.service.auth;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.auth.AuthDAO;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private AuthDAO authDAO;

  @Override // 이메일 중복 확인 비즈니스 로직 설계
  public boolean isUserEmailMath(String email) throws SQLException {
    return authDAO.getUserByEmail(email).isPresent();
  }
  
  

}
