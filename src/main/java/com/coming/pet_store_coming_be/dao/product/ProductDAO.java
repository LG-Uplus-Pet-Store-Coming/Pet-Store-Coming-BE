package com.coming.pet_store_coming_be.dao.product;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductImageDTO;
import com.coming.pet_store_coming_be.dto.product.ProductOptionDTO;

@Mapper
public interface ProductDAO {
  // 상품 추가 관련 DAO
  public void insertProduct(ProductDTO product); // 상품 등록
  public void insertProductOption(ProductOptionDTO product); // 상품 옵션 등록
  public void insertProductImage(ProductImageDTO product); // 상품 이미지 등록
  
  // 상품 정보 변경 관련 DAO
  public void updateProduct(ProductDTO product); // 상품 정보 변경

  public void deleteProduct(String id); // 상품 삭제
}