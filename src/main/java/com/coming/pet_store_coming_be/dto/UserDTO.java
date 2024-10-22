package com.coming.pet_store_coming_be.dto;

public class UserDTO {
 
  private String userIdentifierId;
  private String email;
  private String password;
  private String name;
  private String address;
  private String phoneNumber;
  private String profileImageUrl;
  private String profileImageAlt;
  private String refreshToken;
  private String tokenExpiry;
  private boolean isActive;
  private boolean role;


  public UserDTO() {
  
  }

  public String getUserIdentifierId() {
    return this.userIdentifierId;
  }

  public void setUserIdentifierId(String userIdentifierId) {
    this.userIdentifierId = userIdentifierId;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
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

  public String getRefreshToken() {
    return this.refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getTokenExpiry() {
    return this.tokenExpiry;
  }

  public void setTokenExpiry(String tokenExpiry) {
    this.tokenExpiry = tokenExpiry;
  }

  public boolean isIsActive() {
    return this.isActive;
  }

  public boolean getIsActive() {
    return this.isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public boolean isRole() {
    return this.role;
  }

  public boolean getRole() {
    return this.role;
  }

  public void setRole(boolean role) {
    this.role = role;
  }

}