package com.coming.pet_store_coming_be.service.store;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dto.StoreDTO;
import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.store.StoreDAO;

@Service
public class StoreServiceImpl implements StoreService {

  @Autowired
  StoreDAO dao;

  @Override // 스토어 생성 비즈니스 로직 인스턴스 메서드
  public void createStoreService(StoreDTO store, Map<String, String> fileInfo) throws SQLException {
    store.setThumbnailImagePath(fileInfo.get("filePath"));
    store.setThumbnailImageName(fileInfo.get("fileName"));
    store.setThumbnailImageUrl(fileInfo.get("fileURL"));

    dao.createStore(store);
  }
  
  @Override // 스토어 이름이 중복 여부를 확인하는 비즈니스 로직 인스턴스 메서드
  public boolean isStoreNameDuplicateService(String name) throws SQLException {
    return dao.isStoreNameDuplicate(name);
  }

  @Override // 스토어 정보 가져오기
  public List<StoreDTO> getStoreByUserId(String type, String id) throws SQLException {
    
    Map<String, Object> params = new HashMap<>();
    params.put(type, id);
    
    return dao.getStoreInfo(params);
  }

  @Override // 스토어 정보 수정 비즈니스 로직 인스턴스 메서드
  public void updateStoreInfo(StoreDTO updateStoreInfo) throws SQLException {
    dao.updateStoreInfo(updateStoreInfo);
  }

  @Override // 스토어에 등록된 모든 상품 조회 비즈니스 로직
  public List<ProductDTO>getStoreProductListService(String id) throws SQLException {
    return dao.getStoreProductList(id);
  }

  @Override // 스토어 정보를 삭제하는 비즈니스 로직 인스턴스 메서드
  public void deleteStoreService(String id) throws SQLException {
    dao.deleteStore(id);
  }

  @Override // 스토어 등록 여부를 확인하는 비즈니스 로직 인스턴스 메서드
  public boolean isStoreRegisteredService(String userId) throws SQLException {
    return dao.isStoreRegistered(userId);
  }

}
