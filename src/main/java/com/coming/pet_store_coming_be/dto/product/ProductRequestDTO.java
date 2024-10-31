package com.coming.pet_store_coming_be.dto.product;

import java.util.List;

public class ProductRequestDTO {

  private ProductDTO product;
  private List<ProductOptionDTO> options;
  private List<String> deleteImageIds;

  public ProductRequestDTO() {
  }

  public ProductRequestDTO(ProductDTO product, List<ProductOptionDTO> options, List<String> deleteImageIds) {
    this.product = product;
    this.options = options;
    this.deleteImageIds = deleteImageIds;
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

  public List<String> getDeleteImageIds() {
    return this.deleteImageIds;
  }

  public void setDeleteImageIds(List<String> deleteImageIds) {
    this.deleteImageIds = deleteImageIds;
  }

  @Override
  public String toString() {
    return "{" +
      " product='" + getProduct() + "'" +
      ", options='" + getOptions() + "'" +
      ", deleteImageIds='" + getDeleteImageIds() + "'" +
      "}";
  }

}
