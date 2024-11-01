package com.coming.pet_store_coming_be.dto.canidae;

import java.util.List;

public class CanidaeRequestDTO {

  private CanidaeDTO canidae;
  private List<String> interestProduct;

  public CanidaeRequestDTO() {
  }

  public CanidaeRequestDTO(CanidaeDTO canidae, List<String> interestProduct) {
    this.canidae = canidae;
    this.interestProduct = interestProduct;
  }

  public CanidaeDTO getCanidae() {
    return this.canidae;
  }

  public void setCanidae(CanidaeDTO canidae) {
    this.canidae = canidae;
  }

  public List<String> getInterestProduct() {
    return this.interestProduct;
  }

  public void setInterestProduct(List<String> interestProduct) {
    this.interestProduct = interestProduct;
  }

  @Override
  public String toString() {
    return "{" +
      " canidae='" + getCanidae() + "'" +
      ", interestProduct='" + getInterestProduct() + "'" +
      "}";
  }

}
