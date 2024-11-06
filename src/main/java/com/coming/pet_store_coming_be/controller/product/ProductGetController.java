package com.coming.pet_store_coming_be.controller.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.product.ProductDetailDTO;
import com.coming.pet_store_coming_be.dto.product.ProductInfoDTO;
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


  @GetMapping("/find-all") // 1. 필터링 없는 모든 상품 조회
  public ResponseEntity<Map<String, Object>> getFindAllProductController() {
    Map<String, Object> response = new HashMap<>();

    try {
      List<ProductInfoDTO> data = productService.getFindAllProductService();

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to retrieve all products.");
      response.put("errorCode", "PRODUCT_FETCH_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/find-new-product") // 2. 필터링 없는 모든 상품 조회 - 날짜순으로 정렬
  public ResponseEntity<Map<String, Object>> getFindNewAllProductController() {
    Map<String, Object> response = new HashMap<>();

    try {
      List<ProductInfoDTO> data = productService.getFindNewAllProductService();

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to retrieve all products.");
      response.put("errorCode", "PRODUCT_FETCH_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  // 3.

  @GetMapping("/{id}/find-all") // 4. 특정 카테고리 상품 전체 조회
  public ResponseEntity<Map<String, Object>> getCategoryFindAllController(@PathVariable("id") String categorId) {
    Map<String, Object> response = new HashMap<>();

    try {
      List<ProductInfoDTO> data = productService.getCategoryFindAllService(categorId);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to retrieve all products.");
      response.put("errorCode", "PRODUCT_FETCH_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/{id}/find-new-product") // 5. 특정 카테고리 새 상품 조회
  public ResponseEntity<Map<String, Object>> getCategoryFindNewController(@PathVariable("id") String categorId) {
    Map<String, Object> response = new HashMap<>();
   
    try {
      List<ProductInfoDTO> data = productService.getCategoryFindNewService(categorId);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to retrieve new products.");
      response.put("errorCode", "NEW_PRODUCT_FETCH_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // 6. 특정 카테고리 베스트 상품 조회
  /* 영수형이 결제 관련을 맡고 있고, 상품 리뷰에 대해서 아직 하지 않았기 때문에 아직 베스트 상품에 대한 계산을 하지 못함 -> (베스트 상품 계산 = 리뷰 개수 + 상품 결제 수) limit 10 */

  
  @GetMapping("/{id}/{subId}/find-all") // 7. 서브 카테고리 상품 전체 조회
  public ResponseEntity<Map<String, Object>> getSubCategoryFindAllController(@PathVariable("id") String id ,@PathVariable("subId") String categorId) {
    Map<String, Object> response = new HashMap<>();
   
    try {
      List<ProductInfoDTO> data = productService.getSubCategoryFindAllService(categorId);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to retrieve all products in subcategory.");
      response.put("errorCode", "SUBCATEGORY_PRODUCT_FETCH_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
  @GetMapping("/{id}/{subId}/find-new-product") // 7. 서브 카테고리 새 상품 조회
  public ResponseEntity<Map<String, Object>> getSubCategoryFindNewController(@PathVariable("id") String id, @PathVariable("subId") String categorId) {
    Map<String, Object> response = new HashMap<>();
   
    try {
      List<ProductInfoDTO> data = productService.getSubCategoryFindNewService(categorId);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to retrieve new products in subcategory.");
      response.put("errorCode", "SUBCATEGORY_NEW_PRODUCT_FETCH_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // 8. 서브 카테고리 베스트 상품 조회
  /* 영수형이 결제 관련을 맡고 있고, 상품 리뷰에 대해서 아직 하지 않았기 때문에 아직 베스트 상품에 대한 계산을 하지 못함 -> (베스트 상품 계산 = 리뷰 개수 + 상품 결제 수) limit 10 */

  @GetMapping("/find") // 9. 상품 검색 조회
  public ResponseEntity<Map<String, Object>> getSearchFindProductController(@RequestParam("search") String search) {
    Map<String, Object> response = new HashMap<>();
   
    try {
      List<ProductInfoDTO> data = productService.getSearchFindProductService(search);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to search products.");
      response.put("errorCode", "PRODUCT_SEARCH_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // @GetMapping("/{productId}/detail") // 10. 상품 상세 조회
  // public ResponseEntity<Map<String, Object>> getProductDetailController(@PathVariable("productId") String productId) {
  //   Map<String, Object> response = new HashMap<>();
   
  //   try {
  //     ProductDetailDTO data = productService.getProductDetailService(productId);

  //     response.put("status", HttpStatus.OK.value());
  //     response.put("success", true);
  //     response.put("data", data);

  //     return new ResponseEntity<>(response, HttpStatus.OK);
  //   } catch (Exception e) {
  //     // 실패 응답 보내기
  //     e.printStackTrace();

  //     response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
  //     response.put("success", false);
  //     response.put("message", "Failed to search products.");
  //     response.put("errorCode", "PRODUCT_SEARCH_ERROR");

  //     return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  //   }
  // }
  

}
