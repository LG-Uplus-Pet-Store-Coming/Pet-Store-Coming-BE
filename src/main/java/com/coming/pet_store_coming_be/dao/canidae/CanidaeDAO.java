package com.coming.pet_store_coming_be.dao.canidae;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.canidae.CanidaeDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeInterestProductDTO;

import java.sql.SQLException;

@Mapper
public interface CanidaeDAO {
  // 반려견 정보 등록 관련 DAO
  public void insertCanidaeInfo(CanidaeDTO canidae) throws SQLException;
  public void insertInterestProduct(CanidaeInterestProductDTO interest) throws SQLException;  
  public int getCandiaeLength(String userId) throws SQLException;

  // 반려견 정보 수정 관련 DAO
  public void updateCanidae(CanidaeDTO canidae) throws SQLException;
  public void deleteCanidaeInterestProduct(String id) throws SQLException;
  
  // 반려견 정보 삭제 DAO
  public void deleteCanidaeInfo(String canidaeId) throws SQLException;
}