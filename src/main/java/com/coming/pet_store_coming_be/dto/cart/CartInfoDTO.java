package com.coming.pet_store_coming_be.dto.cart;

public class CartInfoDTO {
  private String cartId;
  private String storeId;
  private String storeBrandName;
  private String productId;
  private String productName;
  private String productQuantity;
  private int productPrice;
  private int productDiscountPrice;
  private String productImageUrl;
  private String productImageAlt;

  public CartInfoDTO() {
  }

  public CartInfoDTO(String cartId, String storeId, String storeBrandName, String productId, String productName,
      String productQuantity, int productPrice, int productDiscountPrice, String productImageUrl,
      String productImageAlt) {
    this.cartId = cartId;
    this.storeId = storeId;
    this.storeBrandName = storeBrandName;
    this.productId = productId;
    this.productName = productName;
    this.productQuantity = productQuantity;
    this.productPrice = productPrice;
    this.productDiscountPrice = productDiscountPrice;
    this.productImageUrl = productImageUrl;
    this.productImageAlt = productImageAlt;
  }

    public String getCartId() {
    return cartId;
  }

  public void setCartId(String cartId) {
    this.cartId = cartId;
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

  public String getProductQuantity() {
    return this.productQuantity;
  }

  public void setProductQuantity(String productQuantity) {
    this.productQuantity = productQuantity;
  }

  public int getProductPrice() {
    return this.productPrice;
  }

  public void setProductPrice(int productPrice) {
    this.productPrice = productPrice;
  }

  public int getProductDiscountPrice() {
    return this.productDiscountPrice;
  }

  public void setProductDiscountPrice(int productDiscountPrice) {
    this.productDiscountPrice = productDiscountPrice;
  }

  public String getProductImageUrl() {
    return this.productImageUrl;
  }

  public void setProductImageUrl(String productImageUrl) {
    this.productImageUrl = productImageUrl;
  }

  public String getProductImageAlt() {
    return this.productImageAlt;
  }

  public void setProductImageAlt(String productImageAlt) {
    this.productImageAlt = productImageAlt;
  }

  @Override
  public String toString() {
    return "{" +
      " storeId='" + getStoreId() + "'" +
      ", storeBrandName='" + getStoreBrandName() + "'" +
      ", productId='" + getProductId() + "'" +
      ", productName='" + getProductName() + "'" +
      ", productQuantity='" + getProductQuantity() + "'" +
      ", productPrice='" + getProductPrice() + "'" +
      ", productDiscountPrice='" + getProductDiscountPrice() + "'" +
      ", productImageUrl='" + getProductImageUrl() + "'" +
      ", productImageAlt='" + getProductImageAlt() + "'" +
      "}";
  }

}
