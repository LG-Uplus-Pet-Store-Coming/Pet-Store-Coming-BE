package com.coming.pet_store_coming_be.dto.product;

import java.time.LocalDateTime;

public class ProductDTO {
  
  private String id;
  private String subCategoryId;
  private String storeId;
  private String name;
  private int price;
  private String description;
  private int discountRate;
  private int discountPrice;
  private String thumbnailImageUrl;
  private String thumbnailImageAlt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public ProductDTO() {
  }

  public ProductDTO(String id, String subCategoryId, String storeId, String name, int price, String description, int discountRate, int discountPrice, String thumbnailImageUrl, String thumbnailImageAlt, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.subCategoryId = subCategoryId;
    this.storeId = storeId;
    this.name = name;
    this.price = price;
    this.description = description;
    this.discountRate = discountRate;
    this.discountPrice = discountPrice;
    this.thumbnailImageUrl = thumbnailImageUrl;
    this.thumbnailImageAlt = thumbnailImageAlt;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSubCategoryId() {
    return this.subCategoryId;
  }

  public void setSubCategoryId(String subCategoryId) {
    this.subCategoryId = subCategoryId;
  }

  public String getStoreId() {
    return this.storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getDiscountRate() {
    return this.discountRate;
  }

  public void setDiscountRate(int discountRate) {
    this.discountRate = discountRate;
  }

  public int getDiscountPrice() {
    return this.discountPrice;
  }

  public void setDiscountPrice(int discountPrice) {
    this.discountPrice = discountPrice;
  }

  public String getThumbnailImageUrl() {
    return this.thumbnailImageUrl;
  }

  public void setThumbnailImageUrl(String thumbnailImageUrl) {
    this.thumbnailImageUrl = thumbnailImageUrl;
  }

  public String getThumbnailImageAlt() {
    return this.thumbnailImageAlt;
  }

  public void setThumbnailImageAlt(String thumbnailImageAlt) {
    this.thumbnailImageAlt = thumbnailImageAlt;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", subCategoryId='" + getSubCategoryId() + "'" +
      ", storeId='" + getStoreId() + "'" +
      ", name='" + getName() + "'" +
      ", price='" + getPrice() + "'" +
      ", description='" + getDescription() + "'" +
      ", discountRate='" + getDiscountRate() + "'" +
      ", discountPrice='" + getDiscountPrice() + "'" +
      ", thumbnailImageUrl='" + getThumbnailImageUrl() + "'" +
      ", thumbnailImageAlt='" + getThumbnailImageAlt() + "'" +
      ", createdAt='" + getCreatedAt() + "'" +
      ", updatedAt='" + getUpdatedAt() + "'" +
      "}";
  }

}
