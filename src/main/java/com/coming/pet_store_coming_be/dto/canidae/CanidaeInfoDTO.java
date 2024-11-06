package com.coming.pet_store_coming_be.dto.canidae;

import java.util.List;

public class CanidaeInfoDTO {
 
  CanidaeDTO canidae;
  List<CanidaeInterestProductDTO> interstProductList;

  public CanidaeInfoDTO() {
  }

  public CanidaeInfoDTO(CanidaeDTO canidae, List<CanidaeInterestProductDTO> interstProductList) {
    this.canidae = canidae;
    this.interstProductList = interstProductList;
  }

  public CanidaeDTO getCanidae() {
    return this.canidae;
  }

  public void setCanidae(CanidaeDTO canidae) {
    this.canidae = canidae;
  }

  public List<CanidaeInterestProductDTO> getInterstProductList() {
    return this.interstProductList;
  }

  public void setInterstProductList(List<CanidaeInterestProductDTO> interstProductList) {
    this.interstProductList = interstProductList;
  }

  @Override
  public String toString() {
    return "{" +
      " canidae='" + getCanidae() + "'" +
      ", interstProductList='" + getInterstProductList() + "'" +
      "}";
  }

}
