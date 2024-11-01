package com.coming.pet_store_coming_be.dto.product;

import java.util.List;

public class ProductRequestDTO {

  private ProductDTO product;
  private List<ProductOptionDTO> options;
  private List<String> deleteImageId;

  public ProductRequestDTO() {
  }

  public ProductRequestDTO(ProductDTO product, List<ProductOptionDTO> options, List<String> deleteImageId) {
    this.product = product;
    this.options = options;
    this.deleteImageId = deleteImageId;
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

  public List<String> getDeleteImageId() {
    return this.deleteImageId;
  }

  public void setDeleteImageId(List<String> deleteImageId) {
    this.deleteImageId = deleteImageId;
  }

  @Override
  public String toString() {
    return "{" +
      " product='" + getProduct() + "'" +
      ", options='" + getOptions() + "'" +
      ", deleteImageId='" + getDeleteImageId() + "'" +
      "}";
  }

}
