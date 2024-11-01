package com.coming.pet_store_coming_be.service.canidae;

import java.util.Map;
import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.canidae.CanidaeRequestDTO;

public interface CanidaeService {
  public void insertCanidaeService(CanidaeRequestDTO canidae, Map<String, String> profileImageInfo) throws SQLException;
  public int getCandiaeLengthService(String userId) throws SQLException; // 사용자가 등록한 반려견의 정보 개수를 가지고 옴
}