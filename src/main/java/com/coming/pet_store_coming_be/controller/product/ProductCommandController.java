package com.coming.pet_store_coming_be.controller.product;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductRequestDTO;
import com.coming.pet_store_coming_be.service.file.FileStorageService;
import com.coming.pet_store_coming_be.service.product.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/product")
public class ProductCommandController {
  
  @Autowired
  FileStorageService fileStorageService;

  @Autowired
  ProductService productService;
  
  @PostMapping("/insert") // 상품 등록 POST Method
  public ResponseEntity<Map<String, Object>> insertProduct(@RequestPart("storeId") String storeId,
    @RequestPart("productRequest") ProductRequestDTO productRequest,
    @RequestPart("thumbnailImage") MultipartFile thumbnailImage,
    @RequestPart("images") List<MultipartFile> images
    ) {
      Map<String, Object> response = new HashMap<>();

      try {

        // 상품의 고유 번호 아이디 부여
        ProductDTO product = productRequest.getProduct();
        product.setId(UUID.randomUUID().toString());

        // 상품 대표 이미지 등록 후 주소, 이름 가져오기
        Map<String, String> fileInto = fileStorageService.saveFile(thumbnailImage, "product/" + product.getId() + "/thumbnail");

        productService.insertProduct(storeId, product, fileInto); // #1. 상품 정보 등록
        productService.insertProductOption(productRequest.getOptions(), product.getId()); // #2. 상품 옵션 추가
        productService.insertProductImage(images, product.getId()); // #3. 상품 이미지 추가

        // 성공 응답 보내기
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


  // 상품 수정 PUT Method

  // 상품 삭제 DELETE Method
  @DeleteMapping("/delete")
  public ResponseEntity<Map<String, Object>> deleteProduct(@RequestParam("id") String productId) {
    Map<String, Object> response = new HashMap<>();
    
    try {
      productService.deleteProduct(productId);

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

}