package com.coming.pet_store_coming_be.controller.canidae;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dto.canidae.CanidaeDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeInfoDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeRequestDTO;
import com.coming.pet_store_coming_be.service.canidae.CanidaeService;
import com.coming.pet_store_coming_be.service.file.S3Service;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/canidae")
public class CanidaeContoller {

  @Autowired
  CanidaeService canidaeService;

  @Autowired
  S3Service s3Service;

  @PostMapping("/insert") // 반려견 정보 등록
  public ResponseEntity<Map<String, Object>> insertCanidaeContoller(@RequestPart("canidaeRequest") CanidaeRequestDTO canidaeRequest, @RequestPart("profilImage") MultipartFile profileImage) {
    Map<String, Object> response = new HashMap<>();

    // 1. 반려견 정보
    //    - 사용자 고유 키를 통해 등록한 반려견의 정보가 있는지 가져옴 -> 이유: 처음 등록한 반려견일 경우 대표 반려견으로 등록하기 위함
    //    - Canidae Info : 작성한 반려견 정보를 가져옴 
    //    - Sub Category Id : 클라이언트에서 관심 항목을 선택한 경우 해당 ID 값을 클라이언트에서 전송 받음 (String)

    // 2. 이미지 정보
    //    - form-data를 통해서 이미지 정보를 불러와야 함

    try {
      // 사용자가 처음 반려견 정보를 등록할 경우 해당 반려견의 대표 반료견으로 선택한다.
      if(canidaeService.getCandiaeLengthService(canidaeRequest.getCanidae().getUserId()) == 0) {
        canidaeRequest.getCanidae().setIsPrimary(true);
      }

      // 해당 반려견 특정 고유 번호 부여
      CanidaeDTO canidae = canidaeRequest.getCanidae();
      canidae.setId(UUID.randomUUID().toString());

      Map<String, String> fileInfo = s3Service.uploadImage(profileImage, "user/" + canidae.getUserId() + "/" + canidae.getId() + "/profile");
      
      // 반려견 정보 등록
      canidaeService.insertCanidaeService(canidaeRequest, fileInfo);
      
      response.put("status", HttpStatus.OK.value());
      response.put("success", true);

      return new ResponseEntity<>(response, HttpStatus.OK);
      
    } catch (Exception e) {
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to register Canidae information.");
      response.put("errorCode", "CANIDAE_REGISTRATION_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  // 반려견 정보 수정
  @PutMapping("/update")
  public ResponseEntity<Map<String, Object>> putMethodName(
    @RequestPart("canidaeRequest") CanidaeRequestDTO canidaeRequest,
    @RequestPart(value = "newProfileImage", required = false) MultipartFile newProfileImage
    ) {
      Map<String, Object> response = new HashMap<>();

      try {
        
        CanidaeDTO canidae = canidaeRequest.getCanidae();

        // 상품 대표 이미지를 변경할 경우
        if(newProfileImage != null && !newProfileImage.isEmpty()) {
          Map<String, String> fileInfo = 
          s3Service.updateImage(
            newProfileImage, 
            canidae.getProfileImagePath(), 
            canidae.getProfileImageName()
          );

          canidae.setProfileImageName(fileInfo.get("fileName"));
          canidae.setProfileImageUrl(fileInfo.get("fileUrl"));
        } else {
          // 반려견 이미지를 변경하지 않는다면 profileImageAlt 값을 null로 수정한다 -> MyBatis의 if 문법을 통해 값 변경 X
          canidae.setProfileImageName(null);
        }

        // profileImageUrl 값을 null로 수정한다 -> MyBatis의 if 문법을 통해 값 변경 X
        canidae.setProfileImagePath(null);

        // #1. 반려견 정보 변경
        canidaeService.updateCanidaeService(canidae, canidaeRequest.getIntersetUpdateProduct());

        response.put("status", HttpStatus.OK.value());
        response.put("success", true);

        return new ResponseEntity<>(response, HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();

        // 실패 응답 보내기
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("success", false);
        response.put("message", "Failed to update Canidae information.");
        response.put("errorCode", "CANIDAE_UPDATE_ERROR");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  // 반려견 정보 삭제
  @DeleteMapping("/delete")
  public ResponseEntity<Map<String, Object>> deleteCanidaeInfoController(@RequestParam("id") String canidaeId) {
    Map<String, Object> response = new HashMap<>();
    
    try {
      CanidaeDTO newPrimaryCanidae = canidaeService.deleteCanidaeInfoService(canidaeId); 

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("newPrimaryCanidae", newPrimaryCanidae);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();

      // 실패 응답 보내기
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to delete Canidae information.");
      response.put("errorCode", "CANIDAE_DELETION_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/list") // 반려견 정보 조회 API
  public ResponseEntity<Map<String, Object>> getCanidaeListController(@RequestParam("user-id") String userId) {
    Map<String, Object> response = new HashMap<>();

    try {
      List<CanidaeInfoDTO> data = canidaeService.getCanidaeListService(userId);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();

      // 실패 응답 보내기
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to retrieve canidae list.");
      response.put("errorCode", "CANIDAE_LIST_RETRIEVAL_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  

}
