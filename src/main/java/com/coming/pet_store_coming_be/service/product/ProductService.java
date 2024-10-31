package com.coming.pet_store_coming_be.service.product;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductOptionDTO;
import com.coming.pet_store_coming_be.dto.product.ProductRequestDTO;

public interface ProductService {

  public void insertProduct(String storeId, ProductDTO product, Map<String, String> fileInfo) throws SQLException; // 상품 등록 비즈니스 로직 인스턴스 메서드
  public void insertProductOption(List<ProductOptionDTO> options, String productId) throws SQLException; // 상품 옵션 등록 비즈니스 로직 인스턴스 메서드
  public void insertProductImage(List<MultipartFile> images, String productId) throws SQLException; // 상품 이미지 등록 비즈니스 로직 인스턴스 메서드

  public void insertProductWithDetails(String storeId, ProductRequestDTO productRequest) throws SQLException; // 상품 등록 비즈니스 로직 인스턴스 메서드
  
}