package com.coming.pet_store_coming_be.service.category.sub_category;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coming.pet_store_coming_be.dao.category.SubCategoryDAO;
import com.coming.pet_store_coming_be.dto.SubCategoryDTO;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
  
  @Autowired
  SubCategoryDAO dao;

  @Override
  public List<SubCategoryDTO> getSubCategoryList() throws SQLException {
    return dao.findAllSubCategoryInfo();
  }

}
