package com.coming.pet_store_coming_be.controller.category;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.MainCategoryDTO;
import com.coming.pet_store_coming_be.service.category.main_category.MainCategoryService;


@RestController
@RequestMapping("/category/main-category")
public class MainCategoryController {

  @Autowired
  MainCategoryService mainCategoryService;
  
  // POST -> 메인 카테고리 생성
  @PostMapping("/create")
  public ResponseEntity<Map<String, Object>> postMainCategoryInfo(@RequestBody MainCategoryDTO category) {
    Map<String, Object> response = new HashMap<>();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // GET -> 메인 카테고리 정보 가져오기
  @GetMapping("/list")
  public ResponseEntity<Map<String, Object>> getMainCategoryList() throws SQLException {
    Map<String, Object> response = new HashMap<>(); // 응답 결과

    try {
      // 모든 메인 카테고리 정보 가져오기
      List<MainCategoryDTO> categorys = mainCategoryService.getMainCategoryList();

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", categorys);

      return new ResponseEntity<>(response, HttpStatus.OK);  
    } catch (Exception e) {

      // 에러가 발생한 경우
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to load main category list.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
