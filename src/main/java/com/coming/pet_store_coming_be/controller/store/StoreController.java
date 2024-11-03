package com.coming.pet_store_coming_be.controller.store;

import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/store")
public class StoreController {
  
  // 1. Store 생성 (Response Data -> StoreDTO, Thumbnail Image)
  @PostMapping("/create")
  public ResponseEntity<Map<String, Object>> createStoreController(@RequestBody String entity) {
    Map<String, Object> response = new HashMap<>();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  

  // 2. Store 수정 (Response Data -> StoreDTO, Thumbnail Image)
  @PutMapping("/update/{storeId}")
  public ResponseEntity<Map<String, Object>> updateStoreController(@PathVariable("storeId") String id, @RequestBody String entity) {
    Map<String, Object> response = new HashMap<>();
      
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // 3. Store 삭제 (Response Data -> Store Id)
  @DeleteMapping("/delete/{storeId}")
  public ResponseEntity<Map<String, Object>> deleteStoreController(@PathVariable("storeId") String id) {
    Map<String, Object> response = new HashMap<>();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  // 4. Store 정보 조회 API
  @GetMapping("/{storeId}")
  public ResponseEntity<Map<String, Object>> getStoreInfoController(@RequestParam("storeId") String id) {
    Map<String, Object> response = new HashMap<>();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  // 5. Store에 등록된 상품 조회 API
  @GetMapping("/{storeId}/products")
  public ResponseEntity<Map<String, Object>> getStoreProductListController(@RequestParam("storeId") String id) {
    Map<String, Object> response = new HashMap<>();  
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  


}
