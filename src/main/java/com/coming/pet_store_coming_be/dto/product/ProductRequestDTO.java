package com.coming.pet_store_coming_be.dto.product;

import java.util.List;

public class ProductRequestDTO {

  private ProductDTO product;
  private List<ProductOptionDTO> productOption;
  private List<ProductImageDTO> productImage;

  public ProductRequestDTO() {
  }

  public ProductRequestDTO(ProductDTO product, List<ProductOptionDTO> productOption, List<ProductImageDTO> productImage) {
    this.product = product;
    this.productOption = productOption;
    this.productImage = productImage;
  }

  public ProductDTO getProduct() {
    return this.product;
  }

  public void setProduct(ProductDTO product) {
    this.product = product;
  }

  public List<ProductOptionDTO> getProductOption() {
    return this.productOption;
  }

  public void setProductOption(List<ProductOptionDTO> productOption) {
    this.productOption = productOption;
  }

  public List<ProductImageDTO> getProductImage() {
    return this.productImage;
  }

  public void setProductImage(List<ProductImageDTO> productImage) {
    this.productImage = productImage;
  }

  @Override
  public String toString() {
    return "{" +
      " product='" + getProduct() + "'" +
      ", productOption='" + getProductOption() + "'" +
      ", productImage='" + getProductImage() + "'" +
      "}";
  }
  
}
