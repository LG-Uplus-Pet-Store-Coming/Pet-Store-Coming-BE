package com.coming.pet_store_coming_be.service.store;

import java.util.Map;
import java.sql.SQLException;

import com.coming.pet_store_coming_be.dto.StoreDTO;

public interface StoreService {

  // 스토어 생성 관련 Service
  public void createStoreService(StoreDTO store, Map<String, String> fileInfo) throws SQLException; // 스토어 생성 비즈니스 로직 인스턴스 메서드
  public boolean isStoreNameDuplicateService(String name) throws SQLException; // 스토어 이름이 중복 여부를 확인하는 비즈니스 로직 인스턴스 메서드

  // 스토어 등록 여부를 확인하는 비즈니스 로직 인스턴스 메서드
  public boolean isStoreRegisteredService(String userId) throws SQLException;
}
