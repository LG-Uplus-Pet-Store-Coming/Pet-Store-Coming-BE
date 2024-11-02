package com.coming.pet_store_coming_be.service.cart;

import java.sql.SQLException;

public interface CartService {

  public boolean isDuplicateProductInCartService(String userId, String productId) throws SQLException; // 사용자의 장바구니에 추가된 상품인지 확인하는 비즈니스 로직 인스턴스 메서드
  
}