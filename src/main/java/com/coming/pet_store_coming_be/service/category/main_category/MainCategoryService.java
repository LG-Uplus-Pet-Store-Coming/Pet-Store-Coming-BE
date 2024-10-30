package com.coming.pet_store_coming_be.service.category.main_category;

import com.coming.pet_store_coming_be.dto.MainCategoryDTO;

import java.util.List;

public interface MainCategoryService {
  public boolean createMainCategory(MainCategoryDTO category); // 메인 카테고리 생성 비즈니스 로직 인스턴스 메서드
  public List<MainCategoryDTO> getMainCategoryList(); // 메인 카테고리 리스트 가져오는 비즈니스 로직 인스턴스 메서드
}