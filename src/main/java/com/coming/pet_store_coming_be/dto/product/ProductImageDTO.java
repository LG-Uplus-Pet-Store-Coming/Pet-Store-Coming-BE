package com.coming.pet_store_coming_be.dto.product;

public class ProductImageDTO {
  
  private String id;
  private String productId;
  private String productImagePath;
  private String productImageName;
  private String productImageURL;

  public ProductImageDTO() {
  }

  public ProductImageDTO(String id, String productId, String productImagePath, String productImageName, String productImageURL) {
    this.id = id;
    this.productId = productId;
    this.productImagePath = productImagePath;
    this.productImageName = productImageName;
    this.productImageURL = productImageURL;
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

  public String getProductImagePath() {
    return this.productImagePath;
  }

  public void setProductImagePath(String productImagePath) {
    this.productImagePath = productImagePath;
  }

  public String getProductImageName() {
    return this.productImageName;
  }

  public void setProductImageName(String productImageName) {
    this.productImageName = productImageName;
  }

  public String getProductImageURL() {
    return this.productImageURL;
  }

  public void setProductImageURL(String productImageURL) {
    this.productImageURL = productImageURL;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", productId='" + getProductId() + "'" +
      ", productImagePath='" + getProductImagePath() + "'" +
      ", productImageName='" + getProductImageName() + "'" +
      ", productImageURL='" + getProductImageURL() + "'" +
      "}";
  }

}
