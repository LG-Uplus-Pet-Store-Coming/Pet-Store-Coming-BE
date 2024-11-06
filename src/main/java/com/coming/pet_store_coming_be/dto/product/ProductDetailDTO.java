package com.coming.pet_store_coming_be.dto.product;

import java.util.List;

public class ProductDetailDTO {
 
  private String storeId;
  private String storeBrandName;
  private String storeThumbnailImagePath;
  private String storeThumbnailImageName;
  private String storeThumbnailImageUrl;

  private String productName;
  private String productDescription;
  private int productPrice;
  private int productDiscountRate;
  private int productDiscountPrice;

  private List<ProductOptionDTO> productOptions;
  private List<ProductImageDTO> productImages;


  public ProductDetailDTO() {
  }

  public ProductDetailDTO(String storeId, String storeBrandName, String storeThumbnailImagePath, String storeThumbnailImageName, String storeThumbnailImageUrl, String productName, String productDescription, int productPrice, int productDiscountRate, int productDiscountPrice, List<ProductOptionDTO> productOptions, List<ProductImageDTO> productImages) {
    this.storeId = storeId;
    this.storeBrandName = storeBrandName;
    this.storeThumbnailImagePath = storeThumbnailImagePath;
    this.storeThumbnailImageName = storeThumbnailImageName;
    this.storeThumbnailImageUrl = storeThumbnailImageUrl;
    this.productName = productName;
    this.productDescription = productDescription;
    this.productPrice = productPrice;
    this.productDiscountRate = productDiscountRate;
    this.productDiscountPrice = productDiscountPrice;
    this.productOptions = productOptions;
    this.productImages = productImages;
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

  public String getStoreThumbnailImagePath() {
    return this.storeThumbnailImagePath;
  }

  public void setStoreThumbnailImagePath(String storeThumbnailImagePath) {
    this.storeThumbnailImagePath = storeThumbnailImagePath;
  }

  public String getStoreThumbnailImageName() {
    return this.storeThumbnailImageName;
  }

  public void setStoreThumbnailImageName(String storeThumbnailImageName) {
    this.storeThumbnailImageName = storeThumbnailImageName;
  }

  public String getStoreThumbnailImageUrl() {
    return this.storeThumbnailImageUrl;
  }

  public void setStoreThumbnailImageUrl(String storeThumbnailImageUrl) {
    this.storeThumbnailImageUrl = storeThumbnailImageUrl;
  }

  public String getProductName() {
    return this.productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductDescription() {
    return this.productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
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

  public List<ProductOptionDTO> getProductOptions() {
    return this.productOptions;
  }

  public void setProductOptions(List<ProductOptionDTO> productOptions) {
    this.productOptions = productOptions;
  }

  public List<ProductImageDTO> getProductImages() {
    return this.productImages;
  }

  public void setProductImages(List<ProductImageDTO> productImages) {
    this.productImages = productImages;
  }

  @Override
  public String toString() {
    return "{" +
      " storeId='" + getStoreId() + "'" +
      ", storeBrandName='" + getStoreBrandName() + "'" +
      ", storeThumbnailImagePath='" + getStoreThumbnailImagePath() + "'" +
      ", storeThumbnailImageName='" + getStoreThumbnailImageName() + "'" +
      ", storeThumbnailImageUrl='" + getStoreThumbnailImageUrl() + "'" +
      ", productName='" + getProductName() + "'" +
      ", productDescription='" + getProductDescription() + "'" +
      ", productPrice='" + getProductPrice() + "'" +
      ", productDiscountRate='" + getProductDiscountRate() + "'" +
      ", productDiscountPrice='" + getProductDiscountPrice() + "'" +
      ", productOptions='" + getProductOptions() + "'" +
      ", productImages='" + getProductImages() + "'" +
      "}";
  }

}
