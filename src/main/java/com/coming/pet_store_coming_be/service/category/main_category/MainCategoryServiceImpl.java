package com.coming.pet_store_coming_be.service.category.main_category;

import com.coming.pet_store_coming_be.dto.MainCategoryDTO;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

  @Override // 메인 카테고리 생성 비즈니스 로직 설계
  public boolean createMainCategory(MainCategoryDTO category) throws SQLException {
    return false;
  }

  @Override // 메인 카테고리 리스트 가져오는 비즈니스 로직 설계
  public List<MainCategoryDTO> getMainCategoryList() throws SQLException {
    
    return new ArrayList<>();
  }
  
}
