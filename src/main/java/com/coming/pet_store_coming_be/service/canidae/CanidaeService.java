package com.coming.pet_store_coming_be.service.canidae;

import java.util.Map;
import java.util.List;
import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.canidae.CanidaeRequestDTO;

public interface CanidaeService {
  public void insertCanidaeService(CanidaeRequestDTO canidae, Map<String, String> profileImageInfo) throws SQLException;
  public void insertCanidaeInterestProductList(List<String> interestProductList) throws SQLException;
}