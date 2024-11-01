package com.coming.pet_store_coming_be.dao.canidae;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.canidae.CanidaeDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeInterestProductDTO;

import java.sql.SQLException;

@Mapper
public interface CanidaeDAO {
  public void insertCanidaeInfo(CanidaeDTO canidae) throws SQLException;
  public void insertInterestProduct(CanidaeInterestProductDTO interest) throws SQLException;  

  public void deleteCanidaeInfo(String canidaeId) throws SQLException;

  public int getCandiaeLength(String userId) throws SQLException;
}