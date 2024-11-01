package com.coming.pet_store_coming_be.dto.canidae;

public class CanidaeIntersetUpdateProductDTO {
  
  private String id;
  private String subCategoryId;
  private String updateStatus;

  public CanidaeIntersetUpdateProductDTO() {
  }

  public CanidaeIntersetUpdateProductDTO(String id, String subCategoryId, String updateStatus) {
    this.id = id;
    this.subCategoryId = subCategoryId;
    this.updateStatus = updateStatus;
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

  public String getUpdateStatus() {
    return this.updateStatus;
  }

  public void setUpdateStatus(String updateStatus) {
    this.updateStatus = updateStatus;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", subCategoryId='" + getSubCategoryId() + "'" +
      ", updateStatus='" + getUpdateStatus() + "'" +
      "}";
  }

}
