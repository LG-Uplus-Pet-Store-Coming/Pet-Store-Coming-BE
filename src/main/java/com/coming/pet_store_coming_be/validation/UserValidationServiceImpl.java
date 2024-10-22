package com.coming.pet_store_coming_be.validation;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.UserDAO;
import com.coming.pet_store_coming_be.dto.UserDTO;

@Service
public class UserValidationServiceImpl implements UserValidationService {

  @Autowired
  UserDAO dao;

  @Override
  public boolean isUserEmailAvailalbe(String email) throws SQLException {
    return dao.getUserByEmail(email).isPresent();
  }

  @Override
  public boolean isPasswordMath(String rawPassword, String encryptedPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.matches(rawPassword, encryptedPassword);
  }

  @Override
  public UserDTO isUserIdentifierMath(String userIdentifier) throws SQLException {
    return dao.getUserByIdentifier(userIdentifier);
  }
  
}