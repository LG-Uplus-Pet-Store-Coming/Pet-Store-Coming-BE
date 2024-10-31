package com.coming.pet_store_coming_be.service.product;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.product.ProductDAO;
import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductImageDTO;
import com.coming.pet_store_coming_be.dto.product.ProductOptionDTO;
import com.coming.pet_store_coming_be.dto.product.ProductRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService{

  @Autowired
  ProductDAO dao;

  @Override
  public void insertProductWithDetails(String storeId, ProductRequestDTO productRequest) throws SQLException {

    String productUUIDValue = UUID.randomUUID().toString(); // 상품에 대한 고유 번호 생성

    // 상품 정보 업데이트
    ProductDTO product = productRequest.getProduct();
    product.setId(productUUIDValue);
    product.setStoreId(storeId);

    // 상품 설명 JSON 객체로 수정
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String jsonDescription = objectMapper.writeValueAsString(product.getDescription());
      product.setDescription(jsonDescription);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    // 1. 상품 정보 등록
    dao.insertProduct(product);

    // 2. 상품 옵션 등록
    if(productRequest.getOptions() != null && !productRequest.getOptions().isEmpty()) {
      // 상품 옵션에 대한 정보는 List 자료형이기 때문에 forEach 를 통해 각 정보를 DB에 저장한다.
      for(ProductOptionDTO option: productRequest.getOptions()) {
        option.setId(UUID.randomUUID().toString());
        option.setProductId(product.getId());

        dao.insertProductOption(option);
      }
    }

    // 3. 상품 이미지 등록
    if(productRequest.getImages() != null && !productRequest.getImages().isEmpty()) {
      // 상품 이미지에 대한 정보는 List 자료형이기 때문에 forEach 를 통해 각 정보를 DB에 저장한다.
      for(ProductImageDTO image: productRequest.getImages()) {
        image.setId(UUID.randomUUID().toString());
        image.setProductId(product.getId());

        dao.insertProductImage(image);
      }
    }


  }
  
  

}
