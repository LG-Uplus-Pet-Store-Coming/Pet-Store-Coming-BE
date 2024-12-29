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

import com.coming.pet_store_coming_be.dto.SubCategoryDTO;
import com.coming.pet_store_coming_be.service.category.sub_category.SubCategoryService;

@RestController
@RequestMapping("/category/sub-category")
public class SubCategoryController {
  
  @Autowired
  SubCategoryService subCategoryService;

  // POST -> 서브 카테고리 생성
  @PostMapping("/create")
  public ResponseEntity<Map<String, Object>> postMainCategoryInfo(@RequestBody SubCategoryDTO category) {
    Map<String, Object> response = new HashMap<>();

    try {

      // 서브 카테고리 생성 비즈니스 로직 처리
      subCategoryService.createSubCategory(category);

      // 서브 카테고리 생성 성공
      response.put("status", HttpStatus.OK.value());
      response.put("success", true);

      return new ResponseEntity<>(response, HttpStatus.OK); // 성공 응답 반환

    } catch(Exception e) {
      // 서브 카테고리 생성 실패
       e.printStackTrace(); // 에러 메세지 출력

       response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
       response.put("success", false);
       response.put("message", "Failed to create sub category.");
       response.put("errorCode", "INTERNAL_SERVER_ERROR");
 
       return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // GET -> 서브 카테고리 정보 가져오기
  @GetMapping("/list")
  public ResponseEntity<Map<String, Object>> subMainCategoryList() throws SQLException {
    Map<String, Object> response = new HashMap<>(); // 응답 결과

    try {
      // 모든 메인 카테고리 정보 가져오기
      List<SubCategoryDTO> categorys = subCategoryService.getSubCategoryList();

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", categorys);

      return new ResponseEntity<>(response, HttpStatus.OK);  
    } catch (Exception e) {

      e.printStackTrace();

      // 에러가 발생한 경우
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to load main category list.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
