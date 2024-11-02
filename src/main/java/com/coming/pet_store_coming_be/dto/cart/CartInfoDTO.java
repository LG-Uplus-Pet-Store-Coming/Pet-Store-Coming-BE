package com.coming.pet_store_coming_be.dto.cart;

public class CartInfoDTO {
 
  private String storeId;
  private String storeBrandName;
  private String productId;
  private String productName;
  private String productQuantity;
  private int productPrice;
  private int productDiscountPrice;

  public CartInfoDTO() {
  }

  public CartInfoDTO(String storeId, String storeBrandName, String productId, String productName, String productQuantity, int productPrice, int productDiscountPrice) {
    this.storeId = storeId;
    this.storeBrandName = storeBrandName;
    this.productId = productId;
    this.productName = productName;
    this.productQuantity = productQuantity;
    this.productPrice = productPrice;
    this.productDiscountPrice = productDiscountPrice;
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
      "}";
  }

}
