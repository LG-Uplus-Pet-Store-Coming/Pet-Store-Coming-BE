package com.coming.pet_store_coming_be.service.cart;

import java.util.Map;
import java.util.HashMap;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.cart.CartDAO;

@Service
public class CartServiceImpl implements CartService {
  
  @Autowired
  CartDAO dao;

  @Override
  public boolean isDuplicateProductInCartService(String userId, String productId) throws SQLException {
    Map<String, Object> params = new HashMap<>();

    params.put("userId", userId);
    params.put("productId", productId);
    
    return dao.isDuplicateProductInCartService(params) > 0;
  }

}
