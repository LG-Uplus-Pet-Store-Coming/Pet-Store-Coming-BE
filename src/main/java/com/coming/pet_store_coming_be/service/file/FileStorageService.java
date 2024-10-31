package com.coming.pet_store_coming_be.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

  public String saveFile(MultipartFile file, String directory); // 이미지 업로드 비즈니스 로직 인터페이스 메서드
  
}