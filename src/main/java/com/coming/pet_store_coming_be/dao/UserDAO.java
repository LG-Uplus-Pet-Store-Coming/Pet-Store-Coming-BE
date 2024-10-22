package com.coming.pet_store_coming_be.dao;

import java.util.Optional;
import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.UserDTO;

@Mapper
public interface UserDAO {
  
  public Optional<UserDTO> getUserByEmail(String email) throws SQLException; // 아이디 중복 DAO
  
  public int insertSignUpUser(UserDTO user) throws SQLException; // 회원가입 DAO

}
