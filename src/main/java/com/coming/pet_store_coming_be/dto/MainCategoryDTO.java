package com.coming.pet_store_coming_be.dto;

public class MainCategoryDTO {
  
  private String id;
  private String name;
  private String slug;
  private String thumbnailPath;
  private String thumbnailName;
  private String thumbnailUrl;

  public MainCategoryDTO() {
  }

  public MainCategoryDTO(String id, String name, String slug, String thumbnailPath, String thumbnailName, String thumbnailUrl) {
    this.id = id;
    this.name = name;
    this.slug = slug;
    this.thumbnailPath = thumbnailPath;
    this.thumbnailName = thumbnailName;
    this.thumbnailUrl = thumbnailUrl;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getThumbnailPath() {
    return this.thumbnailPath;
  }

  public void setThumbnailPath(String thumbnailPath) {
    this.thumbnailPath = thumbnailPath;
  }

  public String getThumbnailName() {
    return this.thumbnailName;
  }

  public void setThumbnailName(String thumbnailName) {
    this.thumbnailName = thumbnailName;
  }

  public String getThumbnailUrl() {
    return this.thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", slug='" + getSlug() + "'" +
      ", thumbnailPath='" + getThumbnailPath() + "'" +
      ", thumbnailName='" + getThumbnailName() + "'" +
      ", thumbnailUrl='" + getThumbnailUrl() + "'" +
      "}";
  }

}
