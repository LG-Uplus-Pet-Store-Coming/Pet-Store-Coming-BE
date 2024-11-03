package com.coming.pet_store_coming_be.dao.cart;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.cart.CartDTO;
import com.coming.pet_store_coming_be.dto.cart.CartInfoDTO;

@Mapper
public interface CartDAO {
  public void insertCartItem(CartDTO cart) throws SQLException;
  public List<CartInfoDTO> getCartItemList(String userId) throws SQLException;
  public void deleteCartItemList(Map<String, String> params) throws SQLException;
  public int isDuplicateProductInCartService(Map<String, Object> params) throws SQLException;
}