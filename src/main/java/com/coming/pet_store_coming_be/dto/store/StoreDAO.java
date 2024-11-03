package com.coming.pet_store_coming_be.dto.store;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreDAO {

  public boolean isStoreRegistered(String userId) throws SQLException;
}