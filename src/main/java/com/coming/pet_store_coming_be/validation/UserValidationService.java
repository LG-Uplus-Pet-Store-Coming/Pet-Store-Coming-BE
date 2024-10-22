package com.coming.pet_store_coming_be.validation;

import java.sql.SQLException;

public interface UserValidationService {

  public boolean isUserEmailAvailalbe(String email) throws SQLException; // 아이디 중복 체크를 위한 유효성 검사 메서드
  
}
