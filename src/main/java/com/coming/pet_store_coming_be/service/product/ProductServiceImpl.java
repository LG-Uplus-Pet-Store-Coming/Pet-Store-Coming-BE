package com.coming.pet_store_coming_be.service.product;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coming.pet_store_coming_be.dao.product.ProductDAO;
import com.coming.pet_store_coming_be.dto.product.ProductDTO;
import com.coming.pet_store_coming_be.dto.product.ProductDetailDTO;
import com.coming.pet_store_coming_be.dto.product.ProductImageDTO;
import com.coming.pet_store_coming_be.dto.product.ProductInfoDTO;
import com.coming.pet_store_coming_be.dto.product.ProductOptionDTO;
import com.coming.pet_store_coming_be.service.file.FileStorageService;
import com.coming.pet_store_coming_be.service.file.S3Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService{

  @Autowired
  FileStorageService fileStorageService;

  @Autowired
  S3Service s3Service;

  @Autowired
  ProductDAO dao;

  @Override // 상품 등록 비즈니스 로직
  public void insertProduct(String storeId, ProductDTO product, Map<String, String> fileInfo) throws SQLException {
    // 상품 정보 업데이트
    product.setStoreId(storeId);
    product.setThumbnailImagePath(fileInfo.get("filePath"));
    product.setThumbnailImageName(fileInfo.get("fileName"));
    product.setThumbnailImageUrl(fileInfo.get("fileURL"));

    // 상품 설명 JSON 객체로 수정
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String jsonDescription = objectMapper.writeValueAsString(product.getDescription());
      product.setDescription(jsonDescription);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 상품 등록
    dao.insertProduct(product); 
  }

  @Override // 상품 옵션 등록 비즈니스 로직
  public void insertProductOption(List<ProductOptionDTO> options, String productId) throws SQLException {
    if(options != null && !options.isEmpty()) {
      for(ProductOptionDTO option: options) {
        option.setId(UUID.randomUUID().toString());
        option.setProductId(productId);

        dao.insertProductOption(option);
      }
    }
  }

  @Override // 상품 이미지 등록 비즈니스 로직
  public void insertProductImage(List<MultipartFile> images, String storeId, String productId) throws SQLException {
    if(images != null && !images.isEmpty()) {
      for(MultipartFile image: images) {

        // 상품 이미지 등록
        Map<String, String> fileInfo = s3Service.uploadImage(image, "store/" + storeId + "/product/" + productId + "/images");

        // ProdcutImageDTO 생성
        ProductImageDTO productImage = 
          new ProductImageDTO(
            UUID.randomUUID().toString(), 
            productId, 
            fileInfo.get("filePath"), 
            fileInfo.get("fileName"),
            fileInfo.get("fileURL")
          );

        System.out.println(productImage);
        
        // 상품 설명 이미지 DB에 등록
        dao.insertProductImage(productImage);

      }
    }
  }

  @Override // 상품 수정 비즈니스 로직 인스턴스 메서드
  public void updateProduct(ProductDTO product) throws SQLException {
    // 상품 설명도 변경될 경우 JSON으로 수정
    if(!product.getDescription().isEmpty()) {
      ObjectMapper objectMapper = new ObjectMapper();
      
      try {
        String jsoString = objectMapper.writeValueAsString(product.getDescription());
        product.setDescription(jsoString);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    dao.updateProduct(product); // 상품 정보 수정
  }

  @Override // 상품 수정 비즈니스 로직 인스턴스 메서드
  public void updateProductOption(List<ProductOptionDTO> options) throws SQLException {
    for(ProductOptionDTO option: options) {
      switch (option.getOperation()) {
        case "ADD": // 새로운 옵션이 추가된 경우
          option.setId(UUID.randomUUID().toString());  
          dao.insertProductOption(option);
          break;
      
        case "UPDATE": // 기존에 있던 옵션의 정보가 수정된 경우
          dao.updateProductOption(option);
          break;

        case "DELETE": // 기존에 있던 옵션의 정보가 삭제된 경우
          dao.deleteProductOption(option.getId());
          break;
      }
    }
  }

  @Override // 필터링 없이 등록된 모든 상품 조회 비즈니스 로직
  public List<ProductInfoDTO> getFindAllProductService() throws SQLException {
    return dao.getFindAllProduct();
  }

  @Override // 필터링 없이 등록된 모든 상품 중 날짜 순 정렬 상품 조회 비즈니스 로직
  public List<ProductInfoDTO> getFindNewAllProductService() throws SQLException {
    return dao.getFindNewAllProductService();
  }

  @Override // 특정 메인 카테고리에 속한 모든 상품 정보 가져오기
  public List<ProductInfoDTO> getCategoryFindAllService(String id) throws SQLException {
    return dao.getCategoryFindAll(id);
  }

  @Override // 특정 메인 카테고리에 속한 모든 상품 정보 가져오기
  public List<ProductInfoDTO> getCategoryFindNewService(String id) throws SQLException {
    return dao.getCategoryFindNew(id);
  }

  @Override // 특정 서브 카테고리에 속한 모든 상품 정보 가져오기
  public List<ProductInfoDTO> getSubCategoryFindAllService(String id) throws SQLException {
    return dao.getSubCategoryFindAll(id);
  }

  @Override // 특정 서브 카테고리에 속한 모든 상품 정보 가져오기
  public List<ProductInfoDTO> getSubCategoryFindNewService(String id) throws SQLException {
    return dao.getSubCategoryFindNew(id);
  }

  @Override // 상품 검색에 의한 상품 정보 가져오기
  public List<ProductInfoDTO> getSearchFindProductService(String search) throws SQLException {
    return dao.searchFindProdcut(search);
  }

  @Override // 상품 이미지 정보 가져오는 비즈니스 로직 인스턴스 메서드
  public ProductImageDTO getProductImage(String id) throws SQLException {
    return dao.getProductImage(id);
  }
  
  @Override // 상품 삭제 비즈니스 로직 인스턴스 메서드
  public void deleteProduct(String id) throws SQLException {
    dao.deleteProduct(id);
  }

  @Override // 상품 이미지 삭제 비즈니스 로직 인스턴스 메서드
  public void deleteProductImage(String id) throws SQLException {
    dao.deleteProductImage(id);
  }

  @Override // 특정 상품 상세 조회 비즈니스 로직 인스턴스 메서드
  public ProductDetailDTO getProductDetailService(String productId) throws SQLException {
    return dao.getProductDetail(productId);
  }

  @Override // 로그인 사용자가 반려견 등록을 한 경우
  public List<ProductInfoDTO> getInterstProductService(String userId) throws SQLException {
    return dao.getInterstProduct(userId);
  }

  @Override
  public List<ProductInfoDTO> getPopularProductService() throws SQLException {
    return dao.getPopularProduct();
  }

}
