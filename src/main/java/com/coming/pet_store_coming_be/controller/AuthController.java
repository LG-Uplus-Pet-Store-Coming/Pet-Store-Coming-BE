package com.coming.pet_store_coming_be.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
  
  @Value("${kakao.client-id}")
  private String clientId;

  @Value("${kakao.redirect-url}")
  private String redirectUri;

  @Value("${kakao.token-url}")
  private String kakaoTokenUrl;

  @PostMapping("/social/kakao") // 카카오 소셜 로그인 API
  public ResponseEntity<?> getKakaoAccessToken(@RequestBody Map<String, String> request) {
    String code = request.get("code"); // 클라이언트에서 받아 온 Kakao 인가 코드 할당
    
    // 카카오 API 요청 시 clientId와 redirectUri 사용
    String tokenUrl = 
      kakaoTokenUrl + "?grant_type=authorization_code&client_id=" + clientId 
      + "&redirect_uri=" + redirectUri + "&code=" + code;

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, null, String.class);
    
    return response;
  }

}
