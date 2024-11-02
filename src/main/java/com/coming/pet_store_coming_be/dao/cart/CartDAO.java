package com.coming.pet_store_coming_be.dao.cart;

import java.util.Map;
import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.cart.CartDTO;

@Mapper
public interface CartDAO {
  public void insertCartItem(CartDTO cart) throws SQLException;
  public int isDuplicateProductInCartService(Map<String, Object> params) throws SQLException;
}