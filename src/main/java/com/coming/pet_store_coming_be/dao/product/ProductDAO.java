package com.coming.pet_store_coming_be.dao.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductImageDTO;
import com.coming.pet_store_coming_be.dto.product.ProductOptionDTO;

@Mapper
public interface ProductDAO {
  // 상품 추가 관련 DAO
  public void insertProduct(ProductDTO product); // 상품 등록
  public void insertProductOption(ProductOptionDTO option); // 상품 옵션 등록
  public void insertProductImage(ProductImageDTO image); // 상품 이미지 등록
  
  // 상품 정보 변경 관련 DAO
  public void updateProduct(ProductDTO product); // 상품 정보 변경
  public void updateProductOption(ProductOptionDTO option); // 상품 옵션 변경

  // 상품 정보 삭제 관련 DAO
  public void deleteProduct(String id); // 상품 삭제
  public void deleteProductOption(String id); // 상품 옵션 삭제
  public void deleteProductImage(String id); // 상품 이미지 삭제

  // 상품 정보 가져오기 관련 DAO
  public List<ProductDTO> getCategoryFindAll(String id); // 특정 메인 카테고리 상품 전체 가져오기
  public List<ProductDTO> getCategoryFindNew(String id); // 특정 메인 카테고리 상품 중 새상품 가져오기
  public ProductImageDTO getProductImage(String id); // 상품 이미지 가져오기
}