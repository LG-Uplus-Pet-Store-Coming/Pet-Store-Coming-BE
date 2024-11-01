package com.coming.pet_store_coming_be.dao.category;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.MainCategoryDTO;

import java.util.List;
import java.sql.SQLException;

@Mapper
public interface MainCategoryDAO {

  public void insertMainCategory(MainCategoryDTO category) throws SQLException; // 메인 카테고리 항목 추가하기
  public List<MainCategoryDTO> findAllMainCategoryInfo() throws SQLException; // 모든 메인 카테고리 정보 불러오기

}