package com.coming.pet_store_coming_be.dao.user;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.coming.pet_store_coming_be.dto.UserDTO;

@Mapper
public interface UserDAO {

  public Map<String, Object> getFindUserEmail(Map<String, Object> params) throws SQLException;
  public void updateUserPassword(Map<String, String> params) throws SQLException;
  public void updateUserInfo(UserDTO user) throws SQLException;
  
}