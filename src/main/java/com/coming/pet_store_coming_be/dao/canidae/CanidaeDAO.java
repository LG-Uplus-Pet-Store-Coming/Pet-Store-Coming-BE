package com.coming.pet_store_coming_be.dao.canidae;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.canidae.CanidaeDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeInfoDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeInterestProductDTO;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CanidaeDAO {
  // 반려견 정보 등록 관련 DAO
  public void insertCanidaeInfo(CanidaeDTO canidae) throws SQLException;
  public void insertInterestProduct(CanidaeInterestProductDTO interest) throws SQLException;  
  public int getCandiaeLength(String userId) throws SQLException;

  // 반려견 정보 수정 관련 DAO
  public void updateCanidae(CanidaeDTO canidae) throws SQLException;
  public void deleteCanidaeInterestProduct(String id) throws SQLException;
  public void resetPrimaryStatus(String userId) throws SQLException;
  
  // 사용자가 등록한 반려견 정보 조회 DAO
  public List<CanidaeInfoDTO> getCanidaeList(String userId) throws SQLException;

  // 반려견 정보 삭제 DAO
  public void deleteCanidaeInfo(String canidaeId) throws SQLException;
  public String getUserIdByCanidaeId(String canidaeId) throws SQLException; // 반려견 제거 후 사용자의 아이디 가져옴
  public List<CanidaeDTO> getRemainingCanidaeByUserId(String userId) throws SQLException; // 사용자의 아이디를 가져온 후 사용자가 등록한 모든 반려견 정보 가지고 옴
}