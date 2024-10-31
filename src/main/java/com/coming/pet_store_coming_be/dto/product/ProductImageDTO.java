package com.coming.pet_store_coming_be.dto.product;

public class ProductImageDTO {
  
  private String id;
  private String productId;
  private String productImageUrl;
  private String productImageAlit;

  public ProductImageDTO() {
  }

  public ProductImageDTO(String id, String productId, String productImageUrl, String productImageAlit) {
    this.id = id;
    this.productId = productId;
    this.productImageUrl = productImageUrl;
    this.productImageAlit = productImageAlit;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProductId() {
    return this.productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductImageUrl() {
    return this.productImageUrl;
  }

  public void setProductImageUrl(String productImageUrl) {
    this.productImageUrl = productImageUrl;
  }

  public String getProductImageAlit() {
    return this.productImageAlit;
  }

  public void setProductImageAlit(String productImageAlit) {
    this.productImageAlit = productImageAlit;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", productId='" + getProductId() + "'" +
      ", productImageUrl='" + getProductImageUrl() + "'" +
      ", productImageAlit='" + getProductImageAlit() + "'" +
      "}";
  }

}
