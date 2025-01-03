package com.coming.pet_store_coming_be.dao.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductDetailDTO;
import com.coming.pet_store_coming_be.dto.product.ProductImageDTO;
import com.coming.pet_store_coming_be.dto.product.ProductInfoDTO;
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
  public List<ProductInfoDTO> getFindAllProduct(); // 필터링 없는 모든 상품 조회
  public List<ProductInfoDTO> getFindNewAllProductService(); // 필터링 없는 날짜 순 모든 상품 조회

  public List<ProductInfoDTO> getCategoryFindAll(String id); // 특정 메인 카테고리 상품 전체 가져오기
  public List<ProductInfoDTO> getCategoryFindNew(String id); // 특정 메인 카테고리 상품 중 새상품 가져오기

  public List<ProductInfoDTO> getSubCategoryFindAll(String id); // 특정 서브 메인 카테고리 상품 전체 가져오기
  public List<ProductInfoDTO> getSubCategoryFindNew(String id); // 특정 서브 메인 카테고리 상품 중 새상품 가져오기

  public List<ProductInfoDTO> searchFindProdcut(String search); // 상품 검색에 의한 상품 정보 가져오기
  public ProductImageDTO getProductImage(String id); // 상품 이미지 가져오기

  public ProductDetailDTO getProductDetail(String productId); // 특정 상품 전체 조회

  // 메인 페이지 접속 시 상품 조회
  public List<ProductInfoDTO> getInterstProduct(String userId);
  public List<ProductInfoDTO> getPopularProduct();
}