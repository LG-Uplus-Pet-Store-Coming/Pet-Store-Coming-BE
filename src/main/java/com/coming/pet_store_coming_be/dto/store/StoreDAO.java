package com.coming.pet_store_coming_be.dto.store;

import java.util.Map;
import java.util.List;
import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.StoreDTO;
import com.coming.pet_store_coming_be.dto.product.ProductDTO;

@Mapper
public interface StoreDAO {
  public void createStore(StoreDTO store) throws SQLException;
  public void deleteStore(String id) throws SQLException;
  public boolean isStoreNameDuplicate(String name) throws SQLException;
  public boolean isStoreRegistered(String userId) throws SQLException;
  
  public void updateStoreInfo(StoreDTO updateStoreInfo) throws SQLException;

  public List<StoreDTO> getStoreInfo(Map<String, Object> params) throws SQLException;
  public List<ProductDTO> getStoreProductList(String id) throws SQLException;
}