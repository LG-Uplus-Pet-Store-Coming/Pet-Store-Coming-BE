package com.coming.pet_store_coming_be.controller.product;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductImageDTO;
import com.coming.pet_store_coming_be.dto.product.ProductRequestDTO;
import com.coming.pet_store_coming_be.service.file.FileStorageService;
import com.coming.pet_store_coming_be.service.file.S3Service;
import com.coming.pet_store_coming_be.service.product.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/product")
public class ProductCommandController {
  
  @Autowired
  FileStorageService fileStorageService;

  @Autowired
  S3Service s3Service;

  @Autowired
  ProductService productService;
  
  @PostMapping("/insert") // 상품 등록 POST Method
  public ResponseEntity<Map<String, Object>> insertProduct(
    @RequestPart("storeId") String storeId,
    @RequestPart("productRequest") ProductRequestDTO productRequest,
    @RequestPart("thumbnailImage") MultipartFile thumbnailImage,
    @RequestPart("images") List<MultipartFile> images
    ) {
      Map<String, Object> response = new HashMap<>();

      try {

        // 상품의 고유 번호 아이디 부여
        ProductDTO product = productRequest.getProduct();
        product.setId(UUID.randomUUID().toString());

        // System.out.println("store/" + storeId + "/product/" + product.getId() + "/thumbnail");

        // 상품 대표 이미지 등록 후 경로, 이미지 이름 가져오기
        Map<String, String> fileInto = s3Service.uploadImage(thumbnailImage, "store/" + storeId + "/product/" + product.getId() + "/thumbnail");

        productService.insertProduct(storeId, product, fileInto); // #1. 상품 정보 등록
        if(productRequest.getOptions() != null && !productRequest.getOptions().isEmpty()) productService.insertProductOption(productRequest.getOptions(), product.getId()); // #2. 상품 옵션 추가
        
        // #3. 상품 이미지 추가 (이미지가 있고 파일이 유효한 경우에만 추가)
        if (images != null && !images.isEmpty()) {
            List<MultipartFile> validImages = images.stream()
                .filter(image -> image != null && !image.isEmpty())
                .collect(Collectors.toList());

            if (!validImages.isEmpty()) {
                productService.insertProductImage(validImages, storeId, product.getId());
            }
        }

        // 성공 응답 보내기
        response.put("status", HttpStatus.OK.value());
        response.put("success", true);

        return new ResponseEntity<>(response, HttpStatus.OK);

      } catch (Exception e) {

        e.printStackTrace();

        // 실패 응답 보내기
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("success", false);
        response.put("message", "Failed to register product.");
        response.put("errorCode", "PRODUCT_REGISTRATION_ERROR");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  } 


  // 상품 수정 PUT Method
  @PutMapping("/update")
  public ResponseEntity<Map<String, Object>> putMethodName(
    @RequestPart("productRequest") ProductRequestDTO productRequest,
    @RequestPart(value = "newThumbnailImage", required = false) MultipartFile newThumbnailImage,
    @RequestPart(value = "newImages", required = false) List<MultipartFile> newImages
  ) {
    Map<String, Object> response = new HashMap<>();

    try {

      ProductDTO product = productRequest.getProduct();
      
      // 상품 대표 이미지를 변경할 경우
      if(newThumbnailImage != null && !newThumbnailImage.isEmpty()) {
        Map<String, String> fileInfo = 
        s3Service.updateImage(
          newThumbnailImage, 
          product.getThumbnailImageUrl(), 
          product.getThumbnailImageAlt()
        );

        product.setThumbnailImageAlt(fileInfo.get("fileName"));
      } else {
        // 상품 대표 이미지를 변경하지 않는다면 thumbnailImageAlt 값을 null로 수정한다 -> MyBatis의 if 문법을 통해 값 변경 X
        product.setThumbnailImageAlt(null);
      }

      // thumbnailImageUrl 값을 null로 수정한다 -> MyBatis의 if 문법을 통해 값 변경 X
      product.setThumbnailImageUrl(null);
      
      productService.updateProduct(product); // #1. 상품 정보 변경

      // #2. 상품 옵션 정보 변경
      if(productRequest.getOptions() != null && !productRequest.getOptions().isEmpty()) productService.updateProductOption(productRequest.getOptions());

      // #3-1. 상품 이미지 삭제
      if(productRequest.getDeleteImageId() != null & !productRequest.getDeleteImageId().isEmpty()) {
        for(String imageId: productRequest.getDeleteImageId()) {
          ProductImageDTO deleteImageFileInfo = productService.getProductImage(imageId); // 이미지 정보 가져오기
          productService.deleteProductImage(imageId); // 이미지 테이블에서 삭제

          // upload 폴더에 이미지 정보 삭제
          s3Service.deleteImage(deleteImageFileInfo.getProductImageUrl(), deleteImageFileInfo.getProductImageAlit());
        }
      }

      // #3-2. 상품 이미지 추가
      if (newImages != null && !newImages.isEmpty()) {
        List<MultipartFile> validImages = newImages.stream()
            .filter(image -> image != null && !image.isEmpty())
            .collect(Collectors.toList());

        if (!validImages.isEmpty()) {
            productService.insertProductImage(validImages, product.getStoreId(), product.getId());
        }
    }

      response.put("status", HttpStatus.OK.value());
      response.put("success", true);

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();

      // 실패 응답 보내기
      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to update product.");
      response.put("errorCode", "PRODUCT_UPDATE_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // 상품 삭제 DELETE Method
  // Work List -> 상품 삭제할 경우 AWS S3에 등록된 해당 상품 관련 모든 이미지(썸네일, 상품 이미지) 삭제시켜야 됨
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
      response.put("message", "Failed to delete product.");
      response.put("errorCode", "PRODUCT_DELETION_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}