package com.coming.pet_store_coming_be.dto.product;

public class ProductInfoDTO {
  
  private String storeId;
  private String storeBrandName;
  private String productId;
  private String productName;
  private int productPrice;
  private int productDiscountRate;
  private int productDiscountPrice;
  private String productThumbnailImagePath;
  private String productThumbnailImageName;
  private String productThumbnailImageUrl;


  public ProductInfoDTO() {
  }

  public ProductInfoDTO(String storeId, String storeBrandName, String productId, String productName, int productPrice, int productDiscountRate, int productDiscountPrice, String productThumbnailImagePath, String productThumbnailImageName, String productThumbnailImageUrl) {
    this.storeId = storeId;
    this.storeBrandName = storeBrandName;
    this.productId = productId;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productDiscountRate = productDiscountRate;
    this.productDiscountPrice = productDiscountPrice;
    this.productThumbnailImagePath = productThumbnailImagePath;
    this.productThumbnailImageName = productThumbnailImageName;
    this.productThumbnailImageUrl = productThumbnailImageUrl;
  }

  public String getStoreId() {
    return this.storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getStoreBrandName() {
    return this.storeBrandName;
  }

  public void setStoreBrandName(String storeBrandName) {
    this.storeBrandName = storeBrandName;
  }

  public String getProductId() {
    return this.productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return this.productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getProductPrice() {
    return this.productPrice;
  }

  public void setProductPrice(int productPrice) {
    this.productPrice = productPrice;
  }

  public int getProductDiscountRate() {
    return this.productDiscountRate;
  }

  public void setProductDiscountRate(int productDiscountRate) {
    this.productDiscountRate = productDiscountRate;
  }

  public int getProductDiscountPrice() {
    return this.productDiscountPrice;
  }

  public void setProductDiscountPrice(int productDiscountPrice) {
    this.productDiscountPrice = productDiscountPrice;
  }

  public String getProductThumbnailImagePath() {
    return this.productThumbnailImagePath;
  }

  public void setProductThumbnailImagePath(String productThumbnailImagePath) {
    this.productThumbnailImagePath = productThumbnailImagePath;
  }

  public String getProductThumbnailImageName() {
    return this.productThumbnailImageName;
  }

  public void setProductThumbnailImageName(String productThumbnailImageName) {
    this.productThumbnailImageName = productThumbnailImageName;
  }

  public String getProductThumbnailImageUrl() {
    return this.productThumbnailImageUrl;
  }

  public void setProductThumbnailImageUrl(String productThumbnailImageUrl) {
    this.productThumbnailImageUrl = productThumbnailImageUrl;
  }

  @Override
  public String toString() {
    return "{" +
      " storeId='" + getStoreId() + "'" +
      ", storeBrandName='" + getStoreBrandName() + "'" +
      ", productId='" + getProductId() + "'" +
      ", productName='" + getProductName() + "'" +
      ", productPrice='" + getProductPrice() + "'" +
      ", productDiscountRate='" + getProductDiscountRate() + "'" +
      ", productDiscountPrice='" + getProductDiscountPrice() + "'" +
      ", productThumbnailImagePath='" + getProductThumbnailImagePath() + "'" +
      ", productThumbnailImageName='" + getProductThumbnailImageName() + "'" +
      ", productThumbnailImageUrl='" + getProductThumbnailImageUrl() + "'" +
      "}";
  }

}
