package com.coming.pet_store_coming_be.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.UserIdentifierDTO;

@Mapper
public interface UserIdentifierDAO {

  // UserIdentifier 데이터 생성 (사용자에 대한 고유 식별자)
  public int createUserIdentifier(UserIdentifierDTO userIdentifier) throws SQLException;
  
}