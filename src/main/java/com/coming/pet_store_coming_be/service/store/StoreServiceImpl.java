package com.coming.pet_store_coming_be.service.store;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dto.StoreDTO;
import com.coming.pet_store_coming_be.dto.store.StoreDAO;

@Service
public class StoreServiceImpl implements StoreService {

  @Autowired
  StoreDAO dao;

  @Override // 스토어 생성 비즈니스 로직 인스턴스 메서드
  public void createStoreService(StoreDTO store, Map<String, String> fileInfo) throws SQLException {
    store.setThumbnailImageUrl(fileInfo.get("filePath"));
    store.setThumbnailImageAlt(fileInfo.get("fileName"));

    dao.createStore(store);
  }
  
  @Override // 스토어 이름이 중복 여부를 확인하는 비즈니스 로직 인스턴스 메서드
  public boolean isStoreNameDuplicateService(String name) throws SQLException {
    return dao.isStoreNameDuplicate(name);
  }

  @Override // 스토어 등록 여부를 확인하는 비즈니스 로직 인스턴스 메서드
  public boolean isStoreRegisteredService(String userId) throws SQLException {
    return dao.isStoreRegistered(userId);
  }

}
