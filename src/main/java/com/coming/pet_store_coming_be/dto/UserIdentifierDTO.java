package com.coming.pet_store_coming_be.dto;

import java.time.LocalDateTime;

public class UserIdentifierDTO {
  
  private String userIdentifierId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public UserIdentifierDTO() {

  }


  public UserIdentifierDTO(String userIdentifierId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.userIdentifierId = userIdentifierId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }


  public String getUserIdentifierId() {
    return this.userIdentifierId.toString();
  }

  public void setUserIdentifierId(String userIdentifierId) {
    this.userIdentifierId = userIdentifierId;
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
      " userIdentifierId='" + getUserIdentifierId() + "'" +
      ", createdAt='" + getCreatedAt() + "'" +
      ", updatedAt='" + getUpdatedAt() + "'" +
      "}";
  }


}
