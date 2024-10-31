package com.coming.pet_store_coming_be.service.product;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dao.product.ProductDAO;
import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductImageDTO;
import com.coming.pet_store_coming_be.dto.product.ProductOptionDTO;
import com.coming.pet_store_coming_be.service.file.FileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService{

  @Autowired
  FileStorageService fileStorageService;

  @Autowired
  ProductDAO dao;

  @Override // 상품 등록 비즈니스 로직
  public void insertProduct(String storeId, ProductDTO product, Map<String, String> fileInfo) throws SQLException {
    // 상품 정보 업데이트
    product.setStoreId(storeId);
    product.setThumbnailImageUrl(fileInfo.get("filePath"));
    product.setThumbnailImageAlt(fileInfo.get("fileName"));

    // 상품 설명 JSON 객체로 수정
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String jsonDescription = objectMapper.writeValueAsString(product.getDescription());
      product.setDescription(jsonDescription);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 상품 등록
    dao.insertProduct(product); 
  }

  @Override // 상품 옵션 등록 비즈니스 로직
  public void insertProductOption(List<ProductOptionDTO> options, String productId) throws SQLException {
    if(options != null && !options.isEmpty()) {
      for(ProductOptionDTO option: options) {
        option.setId(UUID.randomUUID().toString());
        option.setProductId(productId);

        dao.insertProductOption(option);
      }
    }
  }

  @Override // 상품 이미지 등록 비즈니스 로직
  public void insertProductImage(List<MultipartFile> images, String productId) throws SQLException {
    if(images != null && !images.isEmpty()) {
      for(MultipartFile image: images) {

        // 상품 이미지 등록
        Map<String, String> fileInfo = fileStorageService.saveFile(image, "product/" + productId + "/images");

        // ProdcutImageDTO 생성
        ProductImageDTO productImage = new ProductImageDTO(UUID.randomUUID().toString(), productId, fileInfo.get("filePath"), fileInfo.get("fileName"));
        dao.insertProductImage(productImage);
      }
    }
  }

  @Override // 상품 수정 비즈니스 로직 인스턴스 메서드
  public void updateProduct(ProductDTO product) throws SQLException {
    // 상품 설명도 변경될 경우 JSON으로 수정
    if(!product.getDescription().isEmpty()) {
      ObjectMapper objectMapper = new ObjectMapper();
      
      try {
        String jsoString = objectMapper.writeValueAsString(product.getDescription());
        product.setDescription(jsoString);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    dao.updateProduct(product); // 상품 정보 수정
  }
  
  @Override // 상품 삭제 비즈니스 로직 인스턴스 메서드
  public void deleteProduct(String id) throws SQLException {
    dao.deleteProduct(id);
  }

}
