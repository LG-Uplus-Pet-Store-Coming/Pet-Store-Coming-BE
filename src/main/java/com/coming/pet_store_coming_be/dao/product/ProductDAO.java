package com.coming.pet_store_coming_be.dao.product;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductImageDTO;
import com.coming.pet_store_coming_be.dto.product.ProductOptionDTO;

@Mapper
public interface ProductDAO {
  public void insertProduct(ProductDTO product); // 상품 등록
  public void insertProductOption(ProductOptionDTO product); // 상품 등록
  public void insertProductImage(ProductImageDTO product); // 상품 등록
}