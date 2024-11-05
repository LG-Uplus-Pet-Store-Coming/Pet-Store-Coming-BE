package com.coming.pet_store_coming_be.service.file;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
  public Map<String, String> uploadImage(MultipartFile file, String filePath) {
    
    Map<String, String> response = new HashMap<>();

    try {

      // 파일 존재 여부 확인
      if(file == null || file.isEmpty()) {
        throw new IllegalArgumentException("File is required.");
      }

      // 1. 이미지 파일명의 중복 방지를 위해 현재 시간 추가
      String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      String originalImageName = file.getOriginalFilename();
      String newImageName = timestamp + "_" + originalImageName;

      // 2. AWS S3 업로드 경로 설정
      String s3FilePath = filePath + "/" + newImageName; // 파일 경로

      // 3. AWS S3 이미지 업로드
      InputStream inputStream = file.getInputStream();
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(file.getSize());
      metadata.setContentType(file.getContentType()); // Content-Type 설정

      amazonS3.putObject(BUCKET_NAME, s3FilePath, inputStream, metadata);

      // 4. 파일 업로드 후 AWS S3 URL 생성
      String fileURL = amazonS3.getUrl(BUCKET_NAME, s3FilePath).toString();

      response.put("filePath", filePath + "/");
      response.put("fileName", newImageName);
      response.put("fileURL", fileURL);

    } catch (IllegalArgumentException e) {
      response.put("error", "Invalid request: " + e.getMessage());
      
    } catch (IOException e) {
      e.printStackTrace();
      response.put("error", "Failed to save the file due to a server error.");
    }

    return response;
  }

  @Override // 이미지 수정 비즈니스 로직
  public Map<String, String> updateImage(MultipartFile file, String filePath, String deleteFileName) {
    Map<String, String> response = new HashMap<>();

    try {

      // 1. 기존 파일 제거 (S3에서 제거)
      String existingImagePath = filePath + "/" + deleteFileName;
      if(amazonS3.doesObjectExist(BUCKET_NAME, existingImagePath)) {
        amazonS3.deleteObject(BUCKET_NAME, existingImagePath);
      }

      // 2. 새 이미지 파일명 생성 (중복 방지)
      String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      String originalImageName = file.getOriginalFilename();
      String newImageName = timestamp + "_" + originalImageName;

      // 3. AWS S3에 업로드할 경로 설정
      String newImageFilePath = filePath + "/" + newImageName;

      // 4. AWS S3 이미지 업로드
      InputStream inputStream = file.getInputStream();
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(file.getSize());
      metadata.setContentType(file.getContentType()); // Content-Type 설정

      amazonS3.putObject(BUCKET_NAME, newImageFilePath, inputStream, metadata);

      response.put("fileName", newImageName);
      response.put("fileUrl", amazonS3.getUrl(BUCKET_NAME, newImageFilePath).toString());

    } catch (IOException e) {
      e.printStackTrace();
      response.put("error", "File update failed due to an IO error.");
    } catch (Exception e) {
        e.printStackTrace();
        response.put("error", "File update failed.");
    }

    return response;
  }

  @Override
  public void deleteImage(String filePath, String deleteFileName) {
    try {
      
      // AWS S3에서 삭제할 전체 파일 경로 설정
      String s3FilePath = filePath + deleteFileName;

      // AWS S3에 해당 경로로 저장된 이미지가 있을 경우 이미지 삭제
      if(amazonS3.doesObjectExist(BUCKET_NAME, s3FilePath)) {
        amazonS3.deleteObject(BUCKET_NAME, s3FilePath);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
