package com.coming.pet_store_coming_be.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

  public String uploadImage(MultipartFile file);
  
}