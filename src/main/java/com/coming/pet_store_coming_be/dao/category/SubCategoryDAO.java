package com.coming.pet_store_coming_be.dao.category;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.coming.pet_store_coming_be.dto.SubCategoryDTO;


@Mapper
public interface SubCategoryDAO {

  public void insertSubCategory(SubCategoryDTO category) throws SQLException; // 서브 카테고리 항목 추가하기
  public List<SubCategoryDTO> findAllSubCategoryInfo() throws SQLException; // 모든 메인 카테고리 정보 불러오기
  
}