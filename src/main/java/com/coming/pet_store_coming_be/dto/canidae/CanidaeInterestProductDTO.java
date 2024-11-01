package com.coming.pet_store_coming_be.dto.canidae;

public class CanidaeInterestProductDTO {
  private String id;
  private String canidaeId;
  private String subCategoryId;

  public CanidaeInterestProductDTO() {
  }

  public CanidaeInterestProductDTO(String id, String canidaeId, String subCategoryId) {
    this.id = id;
    this.canidaeId = canidaeId;
    this.subCategoryId = subCategoryId;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCanidaeId() {
    return this.canidaeId;
  }

  public void setCanidaeId(String canidaeId) {
    this.canidaeId = canidaeId;
  }

  public String getSubCategoryId() {
    return this.subCategoryId;
  }

  public void setSubCategoryId(String subCategoryId) {
    this.subCategoryId = subCategoryId;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", canidaeId='" + getCanidaeId() + "'" +
      ", subCategoryId='" + getSubCategoryId() + "'" +
      "}";
  }

}
