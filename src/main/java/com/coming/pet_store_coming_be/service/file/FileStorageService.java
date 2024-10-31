package com.coming.pet_store_coming_be.service.file;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

  public Map<String, String> saveFile(MultipartFile file, String directory); // 이미지 업로드 비즈니스 로직 인터페이스 메서드
  public Map<String, String> updateFile(MultipartFile file, String directory, String deleteFileDirectory); // 대표 이미지 변경
  
}