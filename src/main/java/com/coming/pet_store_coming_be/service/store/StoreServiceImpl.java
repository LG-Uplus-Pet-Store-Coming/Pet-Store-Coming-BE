package com.coming.pet_store_coming_be.service.store;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dto.store.StoreDAO;

@Service
public class StoreServiceImpl implements StoreService {

  @Autowired
  StoreDAO dao;

  // 스토어 등록 여부 확인 비즈니스 로직 인스턴스 메서드
  public boolean isStoreRegisteredService(String userId) throws SQLException {
    return dao.isStoreRegistered(userId);
  }

}
