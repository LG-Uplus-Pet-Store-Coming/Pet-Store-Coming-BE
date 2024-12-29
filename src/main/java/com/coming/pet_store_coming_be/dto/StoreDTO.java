package com.coming.pet_store_coming_be.dto;

import java.time.LocalDateTime;

public class StoreDTO {
  
  private String id;
  private String userId;
  private String name;
  private String description;
  private String thumbnailImagePath;
  private String thumbnailImageName;
  private String thumbnailImageUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public StoreDTO() {
  }

  public StoreDTO(String id, String userId, String name, String description, String thumbnailImagePath, String thumbnailImageName, String thumbnailImageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.description = description;
    this.thumbnailImagePath = thumbnailImagePath;
    this.thumbnailImageName = thumbnailImageName;
    this.thumbnailImageUrl = thumbnailImageUrl;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getThumbnailImagePath() {
    return this.thumbnailImagePath;
  }

  public void setThumbnailImagePath(String thumbnailImagePath) {
    this.thumbnailImagePath = thumbnailImagePath;
  }

  public String getThumbnailImageName() {
    return this.thumbnailImageName;
  }

  public void setThumbnailImageName(String thumbnailImageName) {
    this.thumbnailImageName = thumbnailImageName;
  }

  public String getThumbnailImageUrl() {
    return this.thumbnailImageUrl;
  }

  public void setThumbnailImageUrl(String thumbnailImageUrl) {
    this.thumbnailImageUrl = thumbnailImageUrl;
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
      ", userId='" + getUserId() + "'" +
      ", name='" + getName() + "'" +
      ", description='" + getDescription() + "'" +
      ", thumbnailImagePath='" + getThumbnailImagePath() + "'" +
      ", thumbnailImageName='" + getThumbnailImageName() + "'" +
      ", thumbnailImageUrl='" + getThumbnailImageUrl() + "'" +
      ", createdAt='" + getCreatedAt() + "'" +
      ", updatedAt='" + getUpdatedAt() + "'" +
      "}";
  }

}
