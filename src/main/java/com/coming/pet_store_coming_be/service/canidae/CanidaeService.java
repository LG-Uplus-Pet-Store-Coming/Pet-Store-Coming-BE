package com.coming.pet_store_coming_be.service.canidae;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.canidae.CanidaeDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeInfoDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeIntersetUpdateProductDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeRequestDTO;

public interface CanidaeService {
  
  // 반려견 정보 등록 관련 Service
  public void insertCanidaeService(CanidaeRequestDTO canidae, Map<String, String> profileImageInfo) throws SQLException; // 반려견 정보 등록 비즈니스 로직 인스턴스 메서드
  public void deleteCanidaeInfoService(String canidaeId) throws SQLException; // 반려견 정보 삭제 비즈니스 로직 인스턴스 메서드
  public int getCandiaeLengthService(String userId) throws SQLException; // 사용자가 등록한 반려견의 정보 개수를 가지고 옴

  // 사용자가 등록한 모든 반려견 정보 조회 Service
  public List<CanidaeInfoDTO> getCanidaeListService(String userId) throws SQLException;

  // 반려견 정보 변경 관련 Service
  public void updateCanidaeService(CanidaeDTO canidae, List<CanidaeIntersetUpdateProductDTO> list) throws SQLException; // 반려견 정보 수정 비즈니스 로직 인스턴스 메서드
}