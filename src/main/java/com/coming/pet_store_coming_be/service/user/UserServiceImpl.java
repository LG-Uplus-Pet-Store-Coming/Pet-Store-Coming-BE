package com.coming.pet_store_coming_be.service.user;

import java.util.Map;
import java.sql.SQLException;
import java.util.HashMap;

import com.coming.pet_store_coming_be.dao.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceImpl implements UserService {

  @Autowired
  UserDAO dao;

  @Override
  public String getFindUserEmailService(String name, String phoneNumber) throws SQLException {
    Map<String, Object> params = new HashMap<>();

    params.put("name", name);
    params.put("phoneNumber", phoneNumber);
   
    return dao.getFindUserEmail(params);
  }
  
}
