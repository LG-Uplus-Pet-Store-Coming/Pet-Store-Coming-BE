package com.coming.pet_store_coming_be.controller.product;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dto.product.ProductRequestDTO;
import com.coming.pet_store_coming_be.service.product.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")
public class ProductCommandController {
  
  @Autowired
  ProductService productService;

  @PostMapping("/insert-test") // 상품 등록 POST Method - Test 용도
  public ResponseEntity<Map<String, Object>> insertTestProduct(@RequestParam("store_id") String storeId, @RequestBody ProductRequestDTO productRequest) throws SQLException {
    Map<String, Object> response = new HashMap<>();

    // 예외 처리를 통해서 상품 등록에 대한 성공과 실패를 나뉜다.
    try {
      
      // 상품 등록 및 성공 응답 반환
      productService.insertProductWithDetails(storeId, productRequest);


    } catch (Exception e) {
      
      // 상품 등록 실패 시 실패 응답 반환

    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  @PostMapping("/insert") // 상품 등록 POST Method
  public ResponseEntity<Map<String, Object>> insertProduct(@RequestPart("storeId") String storeId,
    @RequestPart("productRequest") ProductRequestDTO productRequest,
    @RequestPart("thumbnailImage") MultipartFile thumbnailImage,
    @RequestPart("images") List<MultipartFile> images
    ) {
      Map<String, Object> response = new HashMap<>();

      try {

        response.put("status", HttpStatus.OK.value());
        response.put("success", true);

        return new ResponseEntity<>(response, HttpStatus.OK);

      } catch (Exception e) {

        e.printStackTrace();

        // 에러가 발생한 경우
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("success", false);
        response.put("message", "Failed to create Product.");
        response.put("errorCode", "INTERNAL_SERVER_ERROR");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  } 


  // 상품 수정 PUT Method

  // 상품 삭제 DELETE Method

}