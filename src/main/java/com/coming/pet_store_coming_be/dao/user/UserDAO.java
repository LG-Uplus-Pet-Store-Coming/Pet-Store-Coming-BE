package com.coming.pet_store_coming_be.dao.user;

import java.sql.SQLException;
import java.util.Map;

public interface UserDAO {

  public String getFindUserEmail(Map<String, Object> params) throws SQLException;
  
}