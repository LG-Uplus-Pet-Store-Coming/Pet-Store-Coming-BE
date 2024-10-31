package com.coming.pet_store_coming_be.service.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  private final String uploadDir = "./upload/";

  @Override // 이미지 업로드 비즈니스 로직 설계
  public String saveFile(MultipartFile file, String fileDirectory) {
    
    try {
      
      // 1. 파일 저장 경로 설정
      String saveFileDir = uploadDir + fileDirectory;
      Files.createDirectories(Paths.get(saveFileDir)); // 경로가 없을면 해당 경로 생성

      // 2. 이미지 파일명의 중복 방지를 위해 현재 시간 추가
      String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      String originalFileName = file.getOriginalFilename();
      String newFileName = originalFileName + "_" + timestamp;

      // 3. 최종 파일 경로 지정
      String filePath = saveFileDir + "/" + newFileName; // 파일 위치 + 사진 이름
      Path path = Paths.get(filePath);

      // 4. 파일 저장
      Files.write(path, file.getBytes());

      return filePath;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return "false";
    }
    
  }
  
}
