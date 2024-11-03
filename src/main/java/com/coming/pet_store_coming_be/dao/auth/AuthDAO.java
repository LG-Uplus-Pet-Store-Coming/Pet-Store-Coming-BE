package com.coming.pet_store_coming_be.dao.auth;

import java.sql.SQLException;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.UserDTO;

@Mapper
public interface AuthDAO {

  public Optional<UserDTO> getUserByEmail(String email) throws SQLException; // 이메일 정보 DB 조회
  public int insertSignUpUser(UserDTO user) throws SQLException; // 회원가입 진행
  public boolean isKakaoUserInfo(Long id) throws SQLException; // 소셜 로그인 사용자 정보 확인

}