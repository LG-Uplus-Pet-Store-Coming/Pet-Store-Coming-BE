package com.coming.pet_store_coming_be.service.product;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductDetailDTO;
import com.coming.pet_store_coming_be.dto.product.ProductImageDTO;
import com.coming.pet_store_coming_be.dto.product.ProductInfoDTO;
import com.coming.pet_store_coming_be.dto.product.ProductOptionDTO;

public interface ProductService {

  public void insertProduct(String storeId, ProductDTO product, Map<String, String> fileInfo) throws SQLException; // 상품 등록 비즈니스 로직 인스턴스 메서드
  public void insertProductOption(List<ProductOptionDTO> options, String productId) throws SQLException; // 상품 옵션 등록 비즈니스 로직 인스턴스 메서드
  public void insertProductImage(List<MultipartFile> images, String storeId, String productId) throws SQLException; // 상품 이미지 등록 비즈니스 로직 인스턴스 메서드

  public void updateProduct(ProductDTO product) throws SQLException; // 상품 수정 비즈니스 로직 인스턴스 메서드
  public void updateProductOption(List<ProductOptionDTO> options) throws SQLException; // 상품 수정 비즈니스 로직 인스턴스 메서드
  
  public void deleteProduct(String id) throws SQLException; // 상품 삭제 비즈니스 로직 인스턴스 메서드
  public void deleteProductImage(String id) throws SQLException; // 상품 이미지 삭제 비즈니스 로직 인스턴스 메서드

  public ProductImageDTO getProductImage(String id) throws SQLException; // 상품 이미지 정보 가져오는 비즈니스 로직 인스턴스 메서드

  // 상품 정보 가져오는 Service 인스턴스 메서드
  
  // ( 필터링 없이 모든 상품 )
  public List<ProductInfoDTO> getFindAllProductService() throws SQLException;
  public List<ProductInfoDTO> getFindNewAllProductService() throws SQLException;
  
  // ( 메인 카테고리 )
  public List<ProductInfoDTO> getCategoryFindAllService(String id) throws SQLException;
  public List<ProductInfoDTO> getCategoryFindNewService(String id) throws SQLException;

  // ( 서브 카테고리 )
  public List<ProductInfoDTO> getSubCategoryFindAllService(String id) throws SQLException;
  public List<ProductInfoDTO> getSubCategoryFindNewService(String id) throws SQLException;

  // ( 상품 검색 )
  public List<ProductInfoDTO> getSearchFindProductService(String search) throws SQLException;

  // ( 특정 상품 상세 조회 )
  public ProductDetailDTO getProductDetailService(String productId) throws SQLException;

  // ( 메인 페이지 접속 시 )
  public List<ProductInfoDTO> getInterstProductService(String userId) throws SQLException;
  public List<ProductInfoDTO> getPopularProductService() throws SQLException;

}