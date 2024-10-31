package com.coming.pet_store_coming_be.service.file;

import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  private final String uploadDir = "./upload/";

  @Override // 이미지 업로드 비즈니스 로직 설계
  public Map<String, String> saveFile(MultipartFile file, String fileDirectory) {
    
    Map<String, String> response = new HashMap<>();

    try {
      // 파일 존재 여부 확인
      if(file == null || file.isEmpty()) {
        throw new IllegalArgumentException("File is required.");
      }

      // 1. 파일 저장 경로 설정
      String saveFileDir = uploadDir + fileDirectory;
      Files.createDirectories(Paths.get(saveFileDir)); // 경로가 없을면 해당 경로 생성

      // 2. 이미지 파일명의 중복 방지를 위해 현재 시간 추가
      String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      String originalFileName = file.getOriginalFilename();
      String newFileName = timestamp + "_" + originalFileName;

      // 3. 최종 파일 경로 지정
      String filePath = saveFileDir + "/" + newFileName; // 파일 위치 + 사진 이름
      Path path = Paths.get(filePath);

      // 4. 파일 저장
      Files.write(path, file.getBytes());

      response.put("filePath", saveFileDir + "/");
      response.put("fileName", newFileName);

      return response;
    } catch (IllegalArgumentException e) {
        response.put("error", "Invalid request: " + e.getMessage());
        return response;
    } catch (IOException e) {
        e.printStackTrace();
        response.put("error", "Failed to save the file due to a server error.");
        return response;
    }
  }

  @Override // 대표 이미지 변경
  public Map<String, String> updateFile(MultipartFile file, String directory, String fileName) {
    Map<String, String> response = new HashMap<>();

    try {
      // 1. 기존 파일 제거
      Path thumbnailPath = Paths.get(directory + fileName);
      if(Files.exists(thumbnailPath)) {
        Files.delete(thumbnailPath);
      }
      
      // 2. 파일 저장 경로 생성 (경로가 없을 경우에만 생성)
      Path saveDirPath = Paths.get(directory);
      if(!Files.exists(saveDirPath)) {
        Files.createDirectories(saveDirPath);
      }

      // 3. 새 이미지 파일명 생성
      String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      String originalFileName = file.getOriginalFilename();
      String newFileName = timestamp + "_" + originalFileName;

      // 4. 최종 파일 경로 지정
      Path newFilePath = saveDirPath.resolve(newFileName);

      // 5. 새 파일 저장
      Files.write(newFilePath, file.getBytes());

      response.put("fileName", newFileName);

    } catch (IOException e) {
      e.printStackTrace();
      response.put("error", "File update failed due to an IO error.");
    } catch (Exception e) {
      e.printStackTrace();
      response.put("error", "File update failed.");
    }

    return response;
  }
  
}
