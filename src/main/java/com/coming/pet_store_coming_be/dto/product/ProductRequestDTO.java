package com.coming.pet_store_coming_be.dto.product;

import java.util.List;

public class ProductRequestDTO {

  private ProductDTO product;
  private List<ProductOptionDTO> options;
  private List<ProductImageDTO> images;

  public ProductRequestDTO() {
  }

  public ProductRequestDTO(ProductDTO product, List<ProductOptionDTO> options, List<ProductImageDTO> images) {
    this.product = product;
    this.options = options;
    this.images = images;
  }

  public ProductDTO getProduct() {
    return this.product;
  }

  public void setProduct(ProductDTO product) {
    this.product = product;
  }

  public List<ProductOptionDTO> getOptions() {
    return this.options;
  }

  public void setOptions(List<ProductOptionDTO> options) {
    this.options = options;
  }

  public List<ProductImageDTO> getImages() {
    return this.images;
  }

  public void setImages(List<ProductImageDTO> images) {
    this.images = images;
  }

  @Override
  public String toString() {
    return "{" +
      " product='" + getProduct() + "'" +
      ", options='" + getOptions() + "'" +
      ", images='" + getImages() + "'" +
      "}";
  }

}
