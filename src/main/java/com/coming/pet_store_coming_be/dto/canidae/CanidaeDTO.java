package com.coming.pet_store_coming_be.dto.canidae;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CanidaeDTO {
  private String id;
  private String userId;
  private String name;
  private LocalDate birth;
  private String breed;
  private String profileImagePath;
  private String profileImageName;
  private String profileImageUrl;
  private Boolean gender;
  private Float weight;
  private Boolean isPrimary;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public CanidaeDTO() {
  }

  public CanidaeDTO(String id, String userId, String name, LocalDate birth, String breed, String profileImagePath, String profileImageName, String profileImageUrl, Boolean gender, Float weight, Boolean isPrimary, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.birth = birth;
    this.breed = breed;
    this.profileImagePath = profileImagePath;
    this.profileImageName = profileImageName;
    this.profileImageUrl = profileImageUrl;
    this.gender = gender;
    this.weight = weight;
    this.isPrimary = isPrimary;
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

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirth() {
    return this.birth;
  }

  public void setBirth(LocalDate birth) {
    this.birth = birth;
  }

  public String getBreed() {
    return this.breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public String getProfileImagePath() {
    return this.profileImagePath;
  }

  public void setProfileImagePath(String profileImagePath) {
    this.profileImagePath = profileImagePath;
  }

  public String getProfileImageName() {
    return this.profileImageName;
  }

  public void setProfileImageName(String profileImageName) {
    this.profileImageName = profileImageName;
  }

  public String getProfileImageUrl() {
    return this.profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public Boolean getGender() {
    return this.gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  public Float getWeight() {
    return this.weight;
  }

  public void setWeight(Float weight) {
    this.weight = weight;
  }

  public Boolean getIsPrimary() {
    return this.isPrimary;
  }

  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
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
      ", name='" + getName() + "'" +
      ", birth='" + getBirth() + "'" +
      ", breed='" + getBreed() + "'" +
      ", profileImagePath='" + getProfileImagePath() + "'" +
      ", profileImageName='" + getProfileImageName() + "'" +
      ", profileImageUrl='" + getProfileImageUrl() + "'" +
      ", gender='" + getGender() + "'" +
      ", weight='" + getWeight() + "'" +
      ", isPrimary='" + getIsPrimary() + "'" +
      ", createdAt='" + getCreatedAt() + "'" +
      ", updatedAt='" + getUpdatedAt() + "'" +
      "}";
  }
 
}
