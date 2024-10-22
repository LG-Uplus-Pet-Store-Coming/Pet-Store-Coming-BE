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
  private String isActive;
  private String role;


  public UserDTO() {
  
  }

  public UserDTO(String userIdentifierId, String email, String password, String name, String address, String phoneNumber, String profileImageUrl, String profileImageAlt, String refreshToken, String tokenExpiry, String isActive, String role) {
    this.userIdentifierId = userIdentifierId;
    this.email = email;
    this.password = password;
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.profileImageUrl = profileImageUrl;
    this.profileImageAlt = profileImageAlt;
    this.refreshToken = refreshToken;
    this.tokenExpiry = tokenExpiry;
    this.isActive = isActive;
    this.role = role;
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

  public String getIsActive() {
    return this.isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }


  @Override
  public String toString() {
    return "{" +
      " userIdentifierId='" + getUserIdentifierId() + "'" +
      ", email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      ", name='" + getName() + "'" +
      ", address='" + getAddress() + "'" +
      ", phoneNumber='" + getPhoneNumber() + "'" +
      ", profileImageUrl='" + getProfileImageUrl() + "'" +
      ", profileImageAlt='" + getProfileImageAlt() + "'" +
      ", refreshToken='" + getRefreshToken() + "'" +
      ", tokenExpiry='" + getTokenExpiry() + "'" +
      ", isActive='" + getIsActive() + "'" +
      ", role='" + getRole() + "'" +
      "}";
  }

}