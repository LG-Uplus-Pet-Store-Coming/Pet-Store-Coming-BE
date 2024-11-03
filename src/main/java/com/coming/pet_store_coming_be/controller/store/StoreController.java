package com.coming.pet_store_coming_be.controller.store;

import java.util.Map;
import java.util.UUID;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dto.StoreDTO;
import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.service.file.FileStorageService;
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

  @Autowired
  FileStorageService fileStorageService;

  // 1. 스토어 생성 여부 확인
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
  
  // 2. Store 생성 (Response Data -> StoreDTO, Thumbnail Image)
  @PostMapping("/create")
  public ResponseEntity<Map<String, Object>> createStoreController(
    @RequestPart("storeRequesst") StoreDTO store, 
    @RequestPart("thumbnailImage") MultipartFile thumbnailImage
    ) {
    Map<String, Object> response = new HashMap<>();

    try {

      // 중복 체크 (동일한 이름을 가진 스토어를 생성하려고 하는 경우)
      if(storeService.isStoreNameDuplicateService(store.getName())) {
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("success", false);
        response.put("message", "A store with this name already exists.");
        response.put("errorCode", "STORE_NAME_DUPLICATE");

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
      }

      store.setId(UUID.randomUUID().toString()); // 스토어 고유 번호 생성

      Map<String, String> fileInfo = fileStorageService.saveFile(thumbnailImage, "/store/" + store.getId() + "/thumbnail"); // 이미지 등록
      storeService.createStoreService(store, fileInfo); // 스토어 등록

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);

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
  

  // 3. Store 수정 (Response Data -> StoreDTO, Thumbnail Image)
  @PutMapping("/update")
  public ResponseEntity<Map<String, Object>> updateStoreInfoController(
    @RequestPart("storeRequest") StoreDTO updateStoreInfo,
    @RequestPart(value = "newThumbnailImage", required = false) MultipartFile newThumbnailImage
    ) {
    Map<String, Object> response = new HashMap<>();
    
    try {

      // 스토어 대표 이미지를 변경할 경우
      if(newThumbnailImage != null && !newThumbnailImage.isEmpty()) {

        Map<String, String> fileInfo =
          fileStorageService.updateFile(
            newThumbnailImage, 
            updateStoreInfo.getThumbnailImageUrl(), 
            updateStoreInfo.getThumbnailImageAlt()
          );

        updateStoreInfo.setThumbnailImageAlt(fileInfo.get("fileName"));
      } else {
        // 스토어 대표 이미지를 변경하지 않는 경우
        updateStoreInfo.setThumbnailImageAlt(null);
      }

      // thumbnailImageUrl 값을 null로 수정한다 -> MyBatis의 if 문법을 통해 값 변경 X
      updateStoreInfo.setThumbnailImageUrl(null);

      storeService.updateStoreInfo(updateStoreInfo);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();

      // 실패 응답 보내기
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to create Product.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
  }

  // 4. Store 삭제 (Response Data -> Store Id)
  @DeleteMapping("/delete/{storeId}")
  public ResponseEntity<Map<String, Object>> deleteStoreController(@PathVariable("storeId") String id) {
    Map<String, Object> response = new HashMap<>();

    try {

      // 이미지 삭제 로직 추가
      storeService.deleteStoreService(id);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);

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
  
  // 5. Store 정보 조회 API
  @GetMapping("/info")
  public ResponseEntity<Map<String, Object>> getMethodName(
    @RequestParam(value="user_id", required=false) String userId,
    @RequestParam(value="store_id", required=false) String storeId
    ) {

      Map<String, Object> response = new HashMap<>();

      try {
        
        List<StoreDTO> data = new ArrayList<>();

        /* 판매자 회원일 경우 */ if(userId != null && storeId == null) data = storeService.getStoreByUserId("userId", userId);
        /* 일반 회원일 경우 */ else if(storeId != null && userId == null) data = storeService.getStoreByUserId("storeId", storeId);

        response.put("status", HttpStatus.OK.value());
        response.put("success", true);
        response.put("data", data);

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
  
  // 6. Store에 등록된 상품 조회 API
  @GetMapping("/{storeId}/products")
  public ResponseEntity<Map<String, Object>> getStoreProductListController(@PathVariable("storeId") String id) {
    Map<String, Object> response = new HashMap<>();

    try {
      
      List<ProductDTO> data = storeService.getStoreProductListService(id);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

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
  


}
