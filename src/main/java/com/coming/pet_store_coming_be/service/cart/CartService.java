package com.coming.pet_store_coming_be.service.cart;

import java.sql.SQLException;
import java.util.List;

import com.coming.pet_store_coming_be.dto.cart.CartDTO;
import com.coming.pet_store_coming_be.dto.cart.CartInfoDTO;

public interface CartService {

  public void insertCartItemService(CartDTO cart) throws SQLException; // 사용자의 장바구니에 상품 정보 추가 비즈니스 로직 인스턴스 메서드
  public List<CartInfoDTO> getCartItemListService(String userId) throws SQLException; // 사용자의 장바구니 등록된 상품 조회 비즈니스 로직 인스턴스 메서드
  public boolean isDuplicateProductInCartService(String userId, String productId) throws SQLException; // 사용자의 장바구니에 추가된 상품인지 확인하는 비즈니스 로직 인스턴스 메서드
  
}