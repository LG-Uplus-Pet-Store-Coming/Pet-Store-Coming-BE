package com.coming.pet_store_coming_be.service.auth;

import java.sql.SQLException;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.auth.AuthDAO;
import com.coming.pet_store_coming_be.dto.UserDTO;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private AuthDAO authDAO;

  @Override // 이메일 중복 확인 비즈니스 로직 설계
  public boolean isUserEmailMath(String email) throws SQLException {
    return authDAO.getUserByEmail(email).isPresent();
  }
  
  // @Override // 회원가입 비즈니스 로직 설계
  public boolean signUpUser(UserDTO user) throws SQLException {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // BCryptPasswordEncoder 인스턴스 생성
    String uuid = UUID.randomUUID().toString(); // 사용자의 고유 번호 생성

    // 사용자 고유 번호, 비밀번호 암호화 한 값으로 Set
    user.setId(uuid);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // 사용자의 정보 등록을 성공한 경우
    if(authDAO.insertSignUpUser(user) > 0) {
      return true;
    }

    // 사용자의 정보 등록을 실패한 경우
    return false;
  }
  

}
