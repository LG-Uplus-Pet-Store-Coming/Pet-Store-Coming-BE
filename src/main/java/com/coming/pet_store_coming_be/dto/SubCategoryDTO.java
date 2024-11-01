package com.coming.pet_store_coming_be.dto;

public class SubCategoryDTO {
  
  private String id;
  private String mainCategoryId;
  private String name;
  private String slug;


  public SubCategoryDTO() {
  }

  public SubCategoryDTO(String id, String mainCategoryId, String name, String slug) {
    this.id = id;
    this.mainCategoryId = mainCategoryId;
    this.name = name;
    this.slug = slug;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMainCategoryId() {
    return this.mainCategoryId;
  }

  public void setMainCategoryId(String mainCategoryId) {
    this.mainCategoryId = mainCategoryId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSlug() {
    return this.slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", mainCategoryId='" + getMainCategoryId() + "'" +
      ", name='" + getName() + "'" +
      ", slug='" + getSlug() + "'" +
      "}";
  }

}
