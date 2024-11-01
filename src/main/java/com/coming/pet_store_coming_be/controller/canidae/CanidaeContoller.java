package com.coming.pet_store_coming_be.controller.canidae;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.canidae.CanidaeRequestDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/canidae")
public class CanidaeContoller {

  @PostMapping("/insert") // 반려견 정보 등록 Contoller
  public ResponseEntity<Map<String, Object>> insertCanidaeContoller(@RequestBody CanidaeRequestDTO canidae) {
    Map<String, Object> response = new HashMap<>();

    System.out.println(canidae.getCanidae());
    System.out.println(canidae.getInterestProduct());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
