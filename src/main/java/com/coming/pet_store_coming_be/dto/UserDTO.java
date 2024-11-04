package com.coming.pet_store_coming_be.dto;

import java.time.LocalDateTime;

public class UserDTO {
 
  private String id;
  private String email;
  private String password;
  private String name;
  private String phoneNumber;
  private String address;
  private String role;
  private String platform;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public UserDTO() {
  }

  public UserDTO(String id, String email, String password, String name, String phoneNumber, String address, String role, String platform, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.role = role;
    this.platform = platform;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
  

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPlatform() {
    return this.platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
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
      ", email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      ", name='" + getName() + "'" +
      ", phoneNumber='" + getPhoneNumber() + "'" +
      ", address='" + getAddress() + "'" +
      ", role='" + getRole() + "'" +
      ", platform='" + getPlatform() + "'" +
      ", createdAt='" + getCreatedAt() + "'" +
      ", updatedAt='" + getUpdatedAt() + "'" +
      "}";
  }

}