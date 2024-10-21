package com.coming.pet_store_coming_be.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
  
  // 아이디 중복을 위한 DAO
  public boolean getUserByEmail(String email) throws SQLException;
  
}
