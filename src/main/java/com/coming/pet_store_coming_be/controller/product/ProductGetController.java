package com.coming.pet_store_coming_be.controller.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.service.product.ProductService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/product")
public class ProductGetController {
  
  @Autowired
  ProductService productService;

  @GetMapping("/{id}/find-all") // 1. 특정 카테고리 상품 전체 조회
  public ResponseEntity<Map<String, Object>> getCategoryFindAllController(@PathVariable("id") String categorId) {
    Map<String, Object> response = new HashMap<>();
   
    try {
      List<ProductDTO> data = productService.getCategoryFindAllService(categorId);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to Find all Product.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  // 2. 특정 카테고리 베스트 상품 조회
  /* 영수형이 결제 관련을 맡고 있기 때문에 아직 베스트 상품에 대한 계산을 하지 못함 -> 베스트 상품 계산 = 리뷰 개수 + 상품 결제 수 limit 10 */

  // 3. 특정 카테고리 새 상품 조회
  @GetMapping("/{id}/find-new-product") // 1. 특정 카테고리 상품 전체 조회
  public ResponseEntity<Map<String, Object>> getCategoryFindNewController(@PathVariable("id") String categorId) {
    Map<String, Object> response = new HashMap<>();
   
    try {
      List<ProductDTO> data = productService.getCategoryFindNewService(categorId);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to Find all Product.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // 4. 상품 검색 조회

}
