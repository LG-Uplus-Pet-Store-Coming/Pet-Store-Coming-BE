package com.coming.pet_store_coming_be.service.category.main_category;

import com.coming.pet_store_coming_be.dao.category.MainCategoryDAO;
import com.coming.pet_store_coming_be.dto.MainCategoryDTO;

import java.util.Map;
import java.util.List;
import java.util.UUID;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

  @Autowired
  private MainCategoryDAO dao;

  @Override // 메인 카테고리 생성 비즈니스 로직 설계
  public void createMainCategory(MainCategoryDTO category, Map<String, String> fileInfo) throws SQLException {
    // 메인 카테고리 정보 업데이트
      category.setId(UUID.randomUUID().toString());
      category.setThumbnailUrl(fileInfo.get("filePath"));
      category.setThumbnailAlt(fileInfo.get("fileName"));

      dao.insertMainCategory(category);
  }

  @Override // 메인 카테고리 리스트 가져오는 비즈니스 로직 설계
  public List<MainCategoryDTO> getMainCategoryList() throws SQLException {
    return dao.findAllMainCategoryInfo();
  }
  
}
