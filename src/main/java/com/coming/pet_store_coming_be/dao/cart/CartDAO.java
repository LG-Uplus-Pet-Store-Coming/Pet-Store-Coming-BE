package com.coming.pet_store_coming_be.dao.cart;

import java.util.Map;
import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDAO {
  public int isDuplicateProductInCartService(Map<String, Object> params) throws SQLException;
}