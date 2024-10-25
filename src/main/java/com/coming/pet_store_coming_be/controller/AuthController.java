package com.coming.pet_store_coming_be.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/auth")
public class AuthController {
  
  @Value("${kakao.client-id}")
  private String clientId;

  @Value("${kakao.redirect-url}")
  private String redirectUri;

  @Value("${kakao.token-url}")
  private String kakaoTokenUrl;

  @GetMapping("/test")
  public String getMethodName() {
      return "Hello";
  }
  
  @SuppressWarnings("unchecked")
  @PostMapping("/social/kakao") // 카카오 소셜 로그인 API
  public ResponseEntity<Map<String, Object>> getKakaoAccessToken(@RequestBody Map<String, String> request) {
    Map<String, Object> response = new HashMap<>();
    
    String code = request.get("code"); // 클라이언트에서 받아 온 Kakao 인가 코드 할당
    
    // 카카오 API 요청 시 clientId와 redirectUri 사용
    String tokenUrl = 
        kakaoTokenUrl + "?grant_type=authorization_code&client_id=" + clientId 
        + "&redirect_uri=" + redirectUri + "&code=" + code;

    RestTemplate restTemplate = new RestTemplate();
    
    // 카카오 API으로부터 액세스 토큰을 요청
    String tokenResponse = restTemplate.postForEntity(tokenUrl, null, String.class).getBody();

    // ObjectMapper 클래스를 사용하여 JSON 문자열을 Map으로 변환 / 예시 : JSON.parse(JSON.stringify(object))
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> accessTokenData;

    try {
      accessTokenData = objectMapper.readValue(tokenResponse, Map.class);
      response.put("accessToken", accessTokenData);
    } catch (Exception e) {
      response.put("success", false);
      response.put("status", HttpStatus.BAD_REQUEST.value());
      response.put("message", "Failed to parse access token response");

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    response.put("success", true);
    response.put("status", HttpStatus.OK.value());
    response.put("message", "Successfully retrieved token");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
