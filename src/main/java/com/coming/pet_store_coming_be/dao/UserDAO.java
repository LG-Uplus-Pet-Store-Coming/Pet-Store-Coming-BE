package com.coming.pet_store_coming_be.dao;

import java.util.Optional;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.UserDTO;

@Mapper
public interface UserDAO {
  
  public Optional<UserDTO> getUserByEmail(String email) throws SQLException; // 아이디 중복 DAO
  public int insertSignUpUser(UserDTO user) throws SQLException; // 회원가입 DAO
  public void updateRefreshTokenAndExpiry(Map<String, Object> params) throws SQLException; // 로그인 사용자 토큰 정보 업데이트 DAO
  public UserDTO getUserByIdentifier(String userIdentifier) throws SQLException; 
  
}
