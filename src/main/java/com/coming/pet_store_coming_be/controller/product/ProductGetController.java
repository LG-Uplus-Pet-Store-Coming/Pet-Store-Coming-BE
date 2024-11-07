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

import com.coming.pet_store_coming_be.security.TokenProvider;
import com.coming.pet_store_coming_be.service.canidae.CanidaeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/product")
public class ProductGetController {
  
  @Autowired
  ProductService productService;

  @Autowired
  CanidaeService canidaeService;

  @Autowired
  TokenProvider tokenProvider;

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

  @GetMapping("/{productId}/detail") // 10. 상품 상세 조회
  public ResponseEntity<Map<String, Object>> getProductDetailController(@PathVariable("productId") String productId) {
    Map<String, Object> response = new HashMap<>();
   
    try {
      ProductDetailDTO data = productService.getProductDetailService(productId);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", data);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "An error occurred while retrieving product details.");
      response.put("errorCode", "PRODUCT_DETAIL_RETRIEVAL_FAILED");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/list/main") // 메인 페이지 방문 시
  public ResponseEntity<Map<String, Object>> getMainPageProductList(@RequestHeader(value = "Authorization", required = false) String token) {
    Map<String, Object> response = new HashMap<>();

    try {

      // 사용자가 로그인 이후 메인 페이지 방문 시
      if(token != null && token.isBlank()) {

        System.out.println("1. Hello");

        if(token.startsWith("Bearer ")) token = token.substring(7); // 토큰에 'Bearer' 이 포함되어 있을 경우 접두사 제거
        if(!tokenProvider.isTokenInvalid(token)) { // 토큰이 아직 유효할 경우
          String userId = tokenProvider.getUserIdFromToken(token);

          System.out.println("2. token" + token);

          // 사용자 고유키를 가지고 온 경우
          if(userId != null) {
            System.out.println("3. User ID" + userId);

            // 사용자가 반려견을 등록한 경우 - 관심 상품 중 인기 상품
            if(canidaeService.getCanidaeListService(userId) != null) {
              List<ProductInfoDTO> canidaeInterstProduct = productService.getInterstProductService(userId);

              System.out.println("*** Interset Product ***");
              System.out.println(canidaeInterstProduct);

              response.put("status", HttpStatus.OK.value());
              response.put("success", true);
              response.put("data", canidaeInterstProduct);

              return new ResponseEntity<>(response, HttpStatus.OK);
            }

            // 사용자가 반려견을 등록하지 않은 경우 - 인기 상품
            else {
              List<ProductInfoDTO> popularProduct = productService.getPopularProductService();

              System.out.println("** NO!!!! Popular Product **");
              System.out.println(popularProduct);

              response.put("status", HttpStatus.OK.value());
              response.put("success", true);
              response.put("data", popularProduct);

              return new ResponseEntity<>(response, HttpStatus.OK);
            }
          }
        }
      }

      // 비로그인 사용자가 메인 페이지 접속 시
      List<ProductInfoDTO> popularProduct = productService.getPopularProductService();

      System.out.println("** NO!!!! Popular Product **");
      System.out.println(popularProduct);

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("data", popularProduct);

      return new ResponseEntity<>(response, HttpStatus.OK);

    } catch (Exception e) {
      // 실패 응답 보내기
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "An error occurred while retrieving product details.");
      response.put("errorCode", "PRODUCT_DETAIL_RETRIEVAL_FAILED");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
}
