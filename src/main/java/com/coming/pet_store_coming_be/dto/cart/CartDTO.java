package com.coming.pet_store_coming_be.dto.cart;

import java.time.LocalDateTime;

public class CartDTO {
 
  private String id;
  private String userId;
  private String productId;
  private int quantity;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public CartDTO() {
  }

  public CartDTO(String id, String userId, String productId, int quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.userId = userId;
    this.productId = productId;
    this.quantity = quantity;
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

  public String getProductId() {
    return this.productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
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
      ", productId='" + getProductId() + "'" +
      ", quantity='" + getQuantity() + "'" +
      ", createdAt='" + getCreatedAt() + "'" +
      ", updatedAt='" + getUpdatedAt() + "'" +
      "}";
  }

}
