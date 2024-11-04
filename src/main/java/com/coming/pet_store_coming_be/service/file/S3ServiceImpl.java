package com.coming.pet_store_coming_be.service.file;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3ServiceImpl implements S3Service {

  @Autowired
  private AmazonS3 amazonS3;

  @Value("${spring.cloud.aws.s3.bucket}")
  private String BUCKET_NAME;

  @Override // 이미지 업로드 비즈니스 로직
  public String uploadImage(MultipartFile file) {
    
    // "arn:aws:s3:::coming-s3-bucket/*"
    // "arn:aws:s3:::coming-s3-bucket/*"

    try {
      String fileName = file.getOriginalFilename();
      InputStream inputStream = file.getInputStream();
      ObjectMetadata metadata = new ObjectMetadata();

      metadata.setContentLength(file.getSize());
      metadata.setContentType("image/jpeg"); // Content-Type 설정 (필요 시 파일 확장자에 따라 변경)

      amazonS3.putObject(BUCKET_NAME, fileName, inputStream, metadata);

      return amazonS3.getUrl(BUCKET_NAME, fileName).toString();

    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("파일 업로드 실패", e);
    }

  }
  
}
