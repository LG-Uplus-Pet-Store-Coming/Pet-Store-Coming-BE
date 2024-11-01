package com.coming.pet_store_coming_be.service.canidae;

import java.util.Map;
import java.util.UUID;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.canidae.CanidaeDAO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeInterestProductDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeRequestDTO;

@Service
public class CanidaeServiceImpl implements CanidaeService {
  
  @Autowired
  CanidaeDAO dao;

  @Override // 반려견 정보 등록
  public void insertCanidaeService(CanidaeRequestDTO canidae, Map<String, String> profileImageInfo) throws SQLException {
    
    // 반려견 정보 업데이트
    canidae.getCanidae().setProfileImageUrl(profileImageInfo.get("filePath"));
    canidae.getCanidae().setProfileImageAlt(profileImageInfo.get("fileName"));

    // 반려견 정보 등록
    dao.insertCanidaeInfo(canidae.getCanidae());

    // 반려견 관심 상품 등록
    if(canidae.getInterestProduct() != null && !canidae.getInterestProduct().isEmpty()) {
      for(String item: canidae.getInterestProduct()) {
        CanidaeInterestProductDTO interest = new CanidaeInterestProductDTO(UUID.randomUUID().toString(), canidae.getCanidae().getId(), item);
        dao.insertInterestProduct(interest);
      }
    }

  }

  @Override // 반려견 정보 삭제 비즈니스 로직 인스턴스 메서드
  public void deleteCanidaeInfoService(String canidaeId) throws SQLException {
    dao.deleteCanidaeInfo(canidaeId);
  }
  
  @Override // 사용자가 등록한 반려견의 개수 가지고 오기
  public int getCandiaeLengthService(String userId) throws SQLException {
    return dao.getCandiaeLength(userId);
  }

  @Override // 반려견 정보 수정 비즈니스 로직 인스턴스 메서드
  public void updateCanidaeService(CanidaeDTO canidae) throws SQLException {
    dao.updateCanidae(canidae);
  }

}
