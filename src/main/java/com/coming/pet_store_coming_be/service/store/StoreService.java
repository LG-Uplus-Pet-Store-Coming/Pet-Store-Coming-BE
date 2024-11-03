package com.coming.pet_store_coming_be.service.store;

import java.sql.SQLException;

public interface StoreService {
  
  // 스토어 등록 여부 확인 비즈니스 로직 인스턴스 메서드
  public boolean isStoreRegisteredService(String userId) throws SQLException;
}
