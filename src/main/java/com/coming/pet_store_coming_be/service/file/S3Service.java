package com.coming.pet_store_coming_be.service.file;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

  public Map<String, String> uploadImage(MultipartFile file, String filePath); // 이미지 업로드 비즈니스 로직 인스턴스 메서드
  public Map<String, String> updateImage(MultipartFile file, String filePath, String deleteFileName); // 이미지 수정 비즈니스 로직 인스턴스 메서드
  public void deleteImage(String filePath, String deleteFileName); // 이미지 삭제 비즈니스 로직 인스턴스 메서드

}