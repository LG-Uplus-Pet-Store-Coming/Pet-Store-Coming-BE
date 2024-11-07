package com.coming.pet_store_coming_be.dao.user;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

  public String getFindUserEmail(Map<String, Object> params) throws SQLException;
  
}