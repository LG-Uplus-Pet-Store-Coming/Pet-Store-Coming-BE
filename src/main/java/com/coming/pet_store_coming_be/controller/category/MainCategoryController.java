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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dto.MainCategoryDTO;
import com.coming.pet_store_coming_be.service.category.main_category.MainCategoryService;
import com.coming.pet_store_coming_be.service.file.FileStorageService;


@RestController
@RequestMapping("/category/main-category")
public class MainCategoryController {

  @Autowired
  FileStorageService fileStorageService;

  @Autowired
  MainCategoryService mainCategoryService;

  // POST -> 메인 카테고리 생성
  @PostMapping("/create")
  public ResponseEntity<Map<String, Object>> postMainCategoryInfo(@RequestPart("mainCategoryData") MainCategoryDTO category, @RequestPart("thumbnailImage") MultipartFile thumbnailImage) {
    Map<String, Object> response = new HashMap<>();

    try {

      // 1. 클라이언트에서 받아온 썸네일 이미지 /uploda/main-category/thumbnail 디렉토리에 업로드
      Map<String, String> fileInfo = fileStorageService.saveFile(thumbnailImage, "main-category/thumbnail");

      // 2. 디렉토리에 이미지 성공할 경우 상품 정보 등록 비즈니스 로직 처리
      mainCategoryService.createMainCategory(category, fileInfo);

      // 메인 카테고리 생성 성공
      response.put("status", HttpStatus.OK.value());
      response.put("success", true);

      return new ResponseEntity<>(response, HttpStatus.OK); // 성공 응답 반환

    } catch (Exception e) {
      
      // 메인 카테고리 생성 실패
      e.printStackTrace(); // 에러 메세지 출력

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to create main category.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
