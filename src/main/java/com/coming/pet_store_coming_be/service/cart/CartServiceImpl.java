package com.coming.pet_store_coming_be.service.cart;

import java.util.Map;
import java.util.UUID;
import java.util.HashMap;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.cart.CartDAO;
import com.coming.pet_store_coming_be.dto.cart.CartDTO;

@Service
public class CartServiceImpl implements CartService {
  
  @Autowired
  CartDAO dao;

  @Override // 사용자의 장바구니에 상품 정보 추가 비즈니스 로직 설계
  public void insertCartItemService(CartDTO cart) throws SQLException {
    cart.setId(UUID.randomUUID().toString());
    dao.insertCartItem(cart);
  }

  @Override // 사용자의 장바구니에 추가된 상품인지 확인하는 비즈니스 로직 설계
  public boolean isDuplicateProductInCartService(String userId, String productId) throws SQLException {
    Map<String, Object> params = new HashMap<>();

    params.put("userId", userId);
    params.put("productId", productId);
    
    return dao.isDuplicateProductInCartService(params) > 0;
  }

}
