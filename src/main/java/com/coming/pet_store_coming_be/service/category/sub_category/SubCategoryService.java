package com.coming.pet_store_coming_be.service.category.sub_category;

import java.util.List;

import com.coming.pet_store_coming_be.dto.SubCategoryDTO;

import java.sql.SQLException;

public interface SubCategoryService {

  public List<SubCategoryDTO> getSubCategoryList() throws SQLException; // 서브 카테고리 리스트 가져오는 비즈니스 로직 인스턴스 메서드

}