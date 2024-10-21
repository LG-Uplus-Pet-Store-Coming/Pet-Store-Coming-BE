package com.coming.pet_store_coming_be.validation;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.UserDAO;

@Service
public class UserValidationServiceImpl implements UserValidationService {

  @Autowired
  UserDAO dao;

  @Override
  public boolean isUserEmailAvailalbe(String email) throws SQLException {
    
    boolean result = dao.getUserByEmail(email);
    System.out.println(result);

    return false;
  }
  
}