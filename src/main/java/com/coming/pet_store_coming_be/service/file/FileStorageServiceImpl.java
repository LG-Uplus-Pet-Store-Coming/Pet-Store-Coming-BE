package com.coming.pet_store_coming_be.service.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  private final String uploadDir = "/upload/";

  @Override // 이미지 업로드 비즈니스 로직 설계
  public String saveFile(MultipartFile file, String fileDirectory) {
    
    System.out.println(uploadDir + fileDirectory);

    // System.out.println(file);
    System.out.println(fileDirectory);
    
    return "Hello";
  }
  
}
