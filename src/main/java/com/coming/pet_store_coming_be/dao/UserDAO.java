package com.coming.pet_store_coming_be.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.UserDTO;

@Mapper
public interface UserDAO {
  
  public boolean getUserByEmail(String email) throws SQLException; // 아이디 중복 DAO
  
  public int insertSignUpUser(UserDTO user) throws SQLException; // 회원가입 DAO

}
