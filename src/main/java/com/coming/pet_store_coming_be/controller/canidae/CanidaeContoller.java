package com.coming.pet_store_coming_be.controller.canidae;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dto.canidae.CanidaeDTO;
import com.coming.pet_store_coming_be.dto.canidae.CanidaeRequestDTO;
import com.coming.pet_store_coming_be.service.canidae.CanidaeService;
import com.coming.pet_store_coming_be.service.file.FileStorageService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/canidae")
public class CanidaeContoller {

  @Autowired
  CanidaeService canidaeService;

  @Autowired
  FileStorageService fileStorageService;

  @PostMapping("/insert") // 반려견 정보 등록 Contoller
  public ResponseEntity<Map<String, Object>> insertCanidaeContoller(@RequestPart("canidaeRequest") CanidaeRequestDTO canidaeRequest, @RequestPart("profilImage") MultipartFile profileImage) {
    Map<String, Object> response = new HashMap<>();

    // 1. 반려견 정보
    //    - 사용자 고유 키를 통해 등록한 반려견의 정보가 있는지 가져옴 -> 이유: 처음 등록한 반려견일 경우 대표 반려견으로 등록하기 위함
    //    - Canidae Info : 작성한 반려견 정보를 가져옴 
    //    - Sub Category Id : 클라이언트에서 관심 항목을 선택한 경우 해당 ID 값을 클라이언트에서 전송 받음 (String)

    // 2. 이미지 정보
    //    - form-data를 통해서 이미지 정보를 불러와야 함

    try {

      if(canidaeService.getCandiaeLengthService(canidaeRequest.getCanidae().getUserId()) == 0) {
        canidaeRequest.getCanidae().setIsPrimary(true);
      }

      // 해당 반려견 특정 고유 번호 부여
      CanidaeDTO canidae = canidaeRequest.getCanidae();
      canidae.setId(UUID.randomUUID().toString());

      Map<String, String> fileInfo = fileStorageService.saveFile(profileImage, "user/" + canidae.getUserId() + "/" + canidae.getId() + "/profile");
      
      // 반려견 정보 등록
      canidaeService.insertCanidaeService(canidaeRequest, fileInfo);
      
      response.put("status", HttpStatus.OK.value());
      response.put("success", true);

      return new ResponseEntity<>(response, HttpStatus.OK);
      
    } catch (Exception e) {
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to create Canidae.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
