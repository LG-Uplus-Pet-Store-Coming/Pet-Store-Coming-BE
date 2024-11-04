package com.coming.pet_store_coming_be.dto.cart;

public class CartDeleteListItemDTO {
  
  private String cartItemId;
  private String userId;

  public CartDeleteListItemDTO() {
  }

  public CartDeleteListItemDTO(String cartItemId, String userId) {
    this.cartItemId = cartItemId;
    this.userId = userId;
  }

  public String getCartItemId() {
    return this.cartItemId;
  }

  public void setCartItemId(String cartItemId) {
    this.cartItemId = cartItemId;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "{" +
      " cartItemId='" + getCartItemId() + "'" +
      ", userId='" + getUserId() + "'" +
      "}";
  }

}
