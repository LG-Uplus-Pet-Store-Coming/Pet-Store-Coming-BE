package com.coming.pet_store_coming_be.dto;

import java.time.LocalDateTime;

public class StoreDTO {
  
  private String id;
  private String userId;
  private String name;
  private String description;
  private String thumbnailImageUrl;
  private String thumbnailImageAlt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public StoreDTO() {
  }

  public StoreDTO(String id, String userId, String name, String description, String thumbnailImageUrl, String thumbnailImageAlt, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.description = description;
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
      ", userId='" + getUserId() + "'" +
      ", name='" + getName() + "'" +
      ", description='" + getDescription() + "'" +
      ", thumbnailImageUrl='" + getThumbnailImageUrl() + "'" +
      ", thumbnailImageAlt='" + getThumbnailImageAlt() + "'" +
      ", createdAt='" + getCreatedAt() + "'" +
      ", updatedAt='" + getUpdatedAt() + "'" +
      "}";
  }

}
