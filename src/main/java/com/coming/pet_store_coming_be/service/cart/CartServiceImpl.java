package com.coming.pet_store_coming_be.service.cart;

import java.util.Map;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.cart.CartDAO;
import com.coming.pet_store_coming_be.dto.cart.CartDTO;
import com.coming.pet_store_coming_be.dto.cart.CartInfoDTO;

@Service
public class CartServiceImpl implements CartService {
  
  @Autowired
  CartDAO dao;

  @Override // 사용자의 장바구니에 상품 정보 추가 비즈니스 로직 설계
  public void insertCartItemService(CartDTO cart) throws SQLException {
    cart.setId(UUID.randomUUID().toString());
    dao.insertCartItem(cart);
  }

  @Override // 사용자의 장바구니 등록된 상품 조회 비즈니스 로직 인스턴스 메서드
  public List<CartInfoDTO> getCartItemListService(String userId) throws SQLException {
    return dao.Test_getCartItemList(userId);
    // return dao.getCartItemList(userId);
  }

  @Override // 사용자의 장바구니에 등록된 상품 삭제 비즈니스 로직 인스턴스 메서드
  public void deleteCartItemListService(String cartItemId, String userId) throws SQLException {
    Map<String, String> params = new HashMap<>();

    params.put("cartItemId", cartItemId);
    params.put("userId", userId);

    dao.deleteCartItemList(params);
  }

  @Override // 사용자의 장바구니에 추가된 상품인지 확인하는 비즈니스 로직 설계
  public boolean isDuplicateProductInCartService(String userId, String productId) throws SQLException {
    Map<String, Object> params = new HashMap<>();

    params.put("userId", userId);
    params.put("productId", productId);
    
    return dao.isDuplicateProductInCartService(params) > 0;
  }

}
