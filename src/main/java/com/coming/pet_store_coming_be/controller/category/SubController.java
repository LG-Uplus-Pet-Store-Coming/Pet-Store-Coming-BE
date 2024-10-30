package com.coming.pet_store_coming_be.controller.category;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.coming.pet_store_coming_be.dto.MainCategoryDTO;

@ResponseBody
@RequestMapping("/category/sub-category")
public class SubController {
  
  // POST -> 서브 카테고리 생성
  @PostMapping("/create")
  public ResponseEntity<Map<String, Object>> postMainCategoryInfo(@RequestBody MainCategoryDTO category) {
    Map<String, Object> response = new HashMap<>();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // GET -> 서브 카테고리 정보 가져오기
  @GetMapping("/list")
  public ResponseEntity<Map<String, Object>> getMainCategoryList() {
    Map<String, Object> response = new HashMap<>(); // 응답 결과

    // 모든 메인 카테고리 정보 가져오기
    List<MainCategoryDTO> categorys = new ArrayList<>();

    

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
