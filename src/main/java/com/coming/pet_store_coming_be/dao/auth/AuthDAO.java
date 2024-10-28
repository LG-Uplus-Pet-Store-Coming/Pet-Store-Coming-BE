package com.coming.pet_store_coming_be.dao.auth;

import java.sql.SQLException;

import java.util.Optional;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.UserDTO;

@Mapper
public interface AuthDAO {

  public Optional<UserDTO> getUserByEmail(String email) throws SQLException; // 이메일 정보 DB 조회
  public int insertSignUpUser(UserDTO user) throws SQLException; // 회원가입 진행
  public void updateRefreshTokenAndExpiry(Map<String, Object> params) throws SQLException; // 로그인 사용자 토큰 정보 업데이트 DAO

}