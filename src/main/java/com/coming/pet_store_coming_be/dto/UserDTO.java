package com.coming.pet_store_coming_be.dto;

import java.time.LocalDateTime;

public class UserDTO {
 
  private String id;
  private String email;
  private String password;
  private String name;
  private String address;
  private String role;
  private String platform;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public UserDTO() {
  
  }

  public UserDTO(String id, String email, String password, String name, String address, String role, String platform, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.address = address;
    this.role = role;
    this.platform = platform;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "UserDTO [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", address="
        + address + ", role=" + role + ", platform=" + platform + ", createdAt=" + createdAt + ", updatedAt="
        + updatedAt + "]";
  }

}