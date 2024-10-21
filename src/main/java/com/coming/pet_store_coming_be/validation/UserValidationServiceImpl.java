package com.coming.pet_store_coming_be.validation;

import org.springframework.stereotype.Service;

@Service
public class UserValidationServiceImpl implements UserValidationService {

  @Override
  public boolean isUserEmailAvailalbe(String email) {
    
    return false;
  }
  
}