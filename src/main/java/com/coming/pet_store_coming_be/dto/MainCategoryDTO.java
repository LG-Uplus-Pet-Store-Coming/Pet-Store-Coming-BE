package com.coming.pet_store_coming_be.dto;

public class MainCategoryDTO {
  
  private String id;
  private String name;
  private String slug;
  private String thumbnailUrl;
  private String thumbnailAlt;


  public MainCategoryDTO() {
  }

  public MainCategoryDTO(String id, String name, String slug, String thumbnailUrl, String thumbnailAlt) {
    this.id = id;
    this.name = name;
    this.slug = slug;
    this.thumbnailUrl = thumbnailUrl;
    this.thumbnailAlt = thumbnailAlt;
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

  public String getThumbnailUrl() {
    return this.thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

  public String getThumbnailAlt() {
    return this.thumbnailAlt;
  }

  public void setThumbnailAlt(String thumbnailAlt) {
    this.thumbnailAlt = thumbnailAlt;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", slug='" + getSlug() + "'" +
      ", thumbnailUrl='" + getThumbnailUrl() + "'" +
      ", thumbnailAlt='" + getThumbnailAlt() + "'" +
      "}";
  }

}
