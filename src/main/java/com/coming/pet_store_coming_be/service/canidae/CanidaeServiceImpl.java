package com.coming.pet_store_coming_be.service.canidae;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.canidae.CanidaeDAO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeInfoDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeInterestProductDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeIntersetUpdateProductDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeRequestDTO;

@Service
public class CanidaeServiceImpl implements CanidaeService {
  
  @Autowired
  CanidaeDAO dao;

  @Override // 반려견 정보 등록
  public void insertCanidaeService(CanidaeRequestDTO canidae, Map<String, String> profileImageInfo) throws SQLException {
    
    // 반려견 정보 업데이트
    canidae.getCanidae().setProfileImagePath(profileImageInfo.get("filePath"));
    canidae.getCanidae().setProfileImageName(profileImageInfo.get("fileName"));
    canidae.getCanidae().setProfileImageUrl(profileImageInfo.get("fileURL"));

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
    // 삭제하려는 반려견 정보 가져오기
    CanidaeDTO canidaeToDelete = dao.getCanidaeById(canidaeId);
    // CanidaeDTO latestCanidae = null;
    
    // 삭제하는 반려견의 대표 반려견일 경우
    if(Boolean.TRUE.equals(canidaeToDelete.getIsPrimary())) {
      
      // userId를 통해서 사용자가 등록한 모든 반려견 가져오기
      List<CanidaeDTO> remainingCanidaeList = dao.getRemainingCanidaeByUserId(canidaeToDelete.getUserId());

      // 반려견의 정보가 남아있을 경우
      if(!remainingCanidaeList.isEmpty()) {
        CanidaeDTO latestCanidae = remainingCanidaeList.get(remainingCanidaeList.size() - 1);
        
        // 반려견 중 가장 마지막에 등록된 반려견 정보를 가지고 온 후 대표로 수정
        latestCanidae.setIsPrimary(true);
        dao.updateCanidae(latestCanidae);
      }
    }

    // 반려견 정보 삭제
    dao.deleteCanidaeInfo(canidaeToDelete.getId());

    // return latestCanidae;
  }
  
  @Override // 사용자가 등록한 반려견의 개수 가지고 오기
  public int getCandiaeLengthService(String userId) throws SQLException {
    return dao.getCandiaeLength(userId);
  }

  @Override // 반려견 정보 수정 비즈니스 로직 인스턴스 메서드
  public void updateCanidaeService(CanidaeDTO canidae, List<CanidaeIntersetUpdateProductDTO> list) throws SQLException {
    
    // 사용자의 모든 반려견 대표 여부 false로 수정
    if(canidae.getIsPrimary() != null && canidae.getIsPrimary()) {
      dao.resetPrimaryStatus(canidae.getUserId());
    }
    
    // 반려견 관심 상품 수정
    if(list != null && !list.isEmpty()) {
      for(CanidaeIntersetUpdateProductDTO item: list) {
        switch (item.getUpdateStatus()) {
          case "ADD":
            CanidaeInterestProductDTO newInterestProductOption = new CanidaeInterestProductDTO(UUID.randomUUID().toString(), canidae.getId(), item.getSubCategoryId());
            // System.out.println(newInterestProductOption);
            dao.insertInterestProduct(newInterestProductOption);
            break;
        
          case "DELETE":
            dao.deleteCanidaeInterestProduct(item.getId());
            break;
        }
      }
    }
    
    dao.updateCanidae(canidae);
  }

  @Override // 사용자가 등록한 모든 반려견 정보 조회 Service
  public List<CanidaeInfoDTO> getCanidaeListService(String userId) throws SQLException {
    return dao.getCanidaeList(userId);
  }

}
