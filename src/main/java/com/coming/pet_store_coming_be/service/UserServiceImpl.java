package com.coming.pet_store_coming_be.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.UserDAO;
import com.coming.pet_store_coming_be.dao.UserIdentifierDAO;
import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.dto.UserIdentifierDTO;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserIdentifierDAO identifierDAO;

  @Autowired
  UserDAO userDAO;

  @Override // 회원가입 Service
  public boolean signUpUser(UserDTO user) throws SQLException{

    UUID uuid4 = UUID.randomUUID(); // 사용자의 고유 번호 생성

    // 1. User Identifier 테이블에 현재 회원가입을 시도하려는 사용자의 고유 번호 등록
    UserIdentifierDTO identifier = new UserIdentifierDTO(uuid4, LocalDateTime.now(), LocalDateTime.now());

    // 고유 번호 등록을 성공할 경우
    if(identifierDAO.createUserIdentifier(identifier) > 0) {
      user.setUserIdentifierId(uuid4); // 사용자의 기본키 및 외래키에 UUID 값 적용

      // 비밀번호 암호화
      
    }

    return false;
  }

  
  
}