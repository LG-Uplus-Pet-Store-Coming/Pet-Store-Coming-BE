package com.coming.pet_store_coming_be.dto.canidae;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CanidaeDTO {
  private String id;
  private String userIdentifierId;
  private String name;
  private LocalDate birth;
  private String breed;
  private String profileImageUrl;
  private String profileImageAlt;
  private boolean gender;
  private float weight;
  private boolean isPrimary;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public CanidaeDTO() {
  }

  public CanidaeDTO(String id, String userIdentifierId, String name, LocalDate birth, String breed, String profileImageUrl, String profileImageAlt, boolean gender, float weight, boolean isPrimary, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.userIdentifierId = userIdentifierId;
    this.name = name;
    this.birth = birth;
    this.breed = breed;
    this.profileImageUrl = profileImageUrl;
    this.profileImageAlt = profileImageAlt;
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

  public String getUserIdentifierId() {
    return this.userIdentifierId;
  }

  public void setUserIdentifierId(String userIdentifierId) {
    this.userIdentifierId = userIdentifierId;
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

  public String getProfileImageUrl() {
    return this.profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public String getProfileImageAlt() {
    return this.profileImageAlt;
  }

  public void setProfileImageAlt(String profileImageAlt) {
    this.profileImageAlt = profileImageAlt;
  }

  public boolean isGender() {
    return this.gender;
  }

  public boolean getGender() {
    return this.gender;
  }

  public void setGender(boolean gender) {
    this.gender = gender;
  }

  public float getWeight() {
    return this.weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public boolean isIsPrimary() {
    return this.isPrimary;
  }

  public boolean getIsPrimary() {
    return this.isPrimary;
  }

  public void setIsPrimary(boolean isPrimary) {
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
      ", userIdentifierId='" + getUserIdentifierId() + "'" +
      ", name='" + getName() + "'" +
      ", birth='" + getBirth() + "'" +
      ", breed='" + getBreed() + "'" +
      ", profileImageUrl='" + getProfileImageUrl() + "'" +
      ", profileImageAlt='" + getProfileImageAlt() + "'" +
      ", gender='" + isGender() + "'" +
      ", weight='" + getWeight() + "'" +
      ", isPrimary='" + isIsPrimary() + "'" +
      ", createdAt='" + getCreatedAt() + "'" +
      ", updatedAt='" + getUpdatedAt() + "'" +
      "}";
  }
 
}
