package com.coming.pet_store_coming_be.controller.store;

import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dto.StoreDTO;
import com.coming.pet_store_coming_be.service.store.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/store")
public class StoreController {

  @Autowired
  StoreService storeService;

  // 스토어 생성 여부 확인
  @GetMapping("/registered")
  public ResponseEntity<Map<String, Object>> isStoreRegisteredController(@RequestParam("user_id") String userId) {
    Map<String, Object> response = new HashMap<>();
    
    try {
      
      // 사용자가 이미 스토어를 생성한 경우
      if(storeService.isStoreRegisteredService(userId)) {
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("success", false);
        response.put("message", "Store already registered for this user.");
        response.put("errorCode", "STORE_ALREADY_REGISTERED");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
      }

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("message", "Store can be registered for this user.");

      return new ResponseEntity<>(response, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();

      // 실패 응답 보내기
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to delete Product.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  
  // 1. Store 생성 (Response Data -> StoreDTO, Thumbnail Image)
  @PostMapping("/create")
  public ResponseEntity<Map<String, Object>> createStoreController(
    @RequestPart("storeRequesst") StoreDTO store, 
    @RequestPart("thumbnailImage") MultipartFile thumbnailImage
    ) {
    Map<String, Object> response = new HashMap<>();

    try {

      // 중복 체크 (동일한 사용자가 또 다른 )
      
    } catch (Exception e) {
      e.printStackTrace();

      // 실패 응답 보내기
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to delete Product.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

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
  
  // 4. Store 정보 조회 API (판매자 회원)
  @GetMapping
  public String getMethodName(@RequestParam("user_id") String id) {
      return new String();
  }
  

  // 5. Store 정보 조회 API (일반 회원)
  @GetMapping("/{storeId}")
  public ResponseEntity<Map<String, Object>> getStoreInfoController(@RequestParam("storeId") String id) {
    Map<String, Object> response = new HashMap<>();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  // 6. Store에 등록된 상품 조회 API
  @GetMapping("/{storeId}/products")
  public ResponseEntity<Map<String, Object>> getStoreProductListController(@RequestParam("storeId") String id) {
    Map<String, Object> response = new HashMap<>();  
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  


}
