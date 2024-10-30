package com.coming.pet_store_coming_be.dto.product;

public class ProductOptionDTO {

  private String id;
  private String productId;
  private String description;
  private int addPrice;

  public ProductOptionDTO() {
  }

  public ProductOptionDTO(String id, String productId, String description, int addPrice) {
    this.id = id;
    this.productId = productId;
    this.description = description;
    this.addPrice = addPrice;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProductId() {
    return this.productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getAddPrice() {
    return this.addPrice;
  }

  public void setAddPrice(int addPrice) {
    this.addPrice = addPrice;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", productId='" + getProductId() + "'" +
      ", description='" + getDescription() + "'" +
      ", addPrice='" + getAddPrice() + "'" +
      "}";
  }

}
