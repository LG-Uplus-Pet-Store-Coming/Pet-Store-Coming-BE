package com.coming.pet_store_coming_be.dto.canidae;

import java.util.List;

public class CanidaeRequestDTO {

  private CanidaeDTO canidae;
  private List<String> interestProduct;
  private List<CanidaeIntersetUpdateProductDTO> intersetUpdateProduct;

  public CanidaeRequestDTO() {
  }

  public CanidaeRequestDTO(CanidaeDTO canidae, List<String> interestProduct, List<CanidaeIntersetUpdateProductDTO> intersetUpdateProduct) {
    this.canidae = canidae;
    this.interestProduct = interestProduct;
    this.intersetUpdateProduct = intersetUpdateProduct;
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

  public List<CanidaeIntersetUpdateProductDTO> getIntersetUpdateProduct() {
    return this.intersetUpdateProduct;
  }

  public void setIntersetUpdateProduct(List<CanidaeIntersetUpdateProductDTO> intersetUpdateProduct) {
    this.intersetUpdateProduct = intersetUpdateProduct;
  }

  @Override
  public String toString() {
    return "{" +
      " canidae='" + getCanidae() + "'" +
      ", interestProduct='" + getInterestProduct() + "'" +
      ", intersetUpdateProduct='" + getIntersetUpdateProduct() + "'" +
      "}";
  }

}
