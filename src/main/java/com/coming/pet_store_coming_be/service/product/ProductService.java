package com.coming.pet_store_coming_be.service.product;

import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.product.ProductRequestDTO;

public interface ProductService {

  public void insertProductWithDetails(String storeId, ProductRequestDTO productRequest) throws SQLException; // 상품 등록 비즈니스 로직 인스턴스 메서드
  
}