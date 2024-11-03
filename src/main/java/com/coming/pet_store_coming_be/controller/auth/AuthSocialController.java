package com.coming.pet_store_coming_be.controller.auth;

import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/auth/social")
public class AuthSocialController {
  
  @Value("${kakao.client-id}")
  private String clientId;

  @Value("${kakao.redirect-url}")
  private String redirectUri;

  @Value("${kakao.token-url}")
  private String kakaoTokenUrl;

  // http://localhost:8080/auth/social/kakao/request/token?code=${code}

  @GetMapping("/kakao/request/token") // 카카오 소셜 로그인을 위한 AccessToken 발급 여부 API
  public ResponseEntity<Map<String, Object>> requestKakaoToken(@RequestParam("code") String code) {
    
    Map<String, Object> response = new HashMap<>();

    try {
      
      RestTemplate restTemplate = new RestTemplate();

      String tokenUrl = kakaoTokenUrl + "?grant_type=authorization_code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&code=" + code;
      
      // 카카오 API로부터 액세스 토큰을 요청하고 응답을 받음
      String tokenResponse = restTemplate.postForEntity(tokenUrl, null, String.class).getBody();

      // 응답 본문을 JSON 객체로 파싱
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(tokenResponse);

      /*
       * accessToken : 카카오 사용자 정보를 얻어오기 위한 인증 토큰
       * refreshToken : 인증 토큰의 만료 시간을 연장하기 위한 토큰
       * expiresIn : 인증 토큰의 만료 시간
       * refreshTokenExpiresIn : refreshToken의 시간을 연장하기 위한 시간
       */

      // 응답에 필요한 정보 추가
      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("accessToken", jsonNode.get("access_token").asText());
      response.put("refreshToken", jsonNode.get("refresh_token").asText());
      response.put("expiresIn", jsonNode.get("expires_in").asInt());
      response.put("refreshTokenExpiresIn", jsonNode.get("refresh_token_expires_in").asText());

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();

      response.put("success", false);
      response.put("status", HttpStatus.BAD_REQUEST.value());
      response.put("message", "Failed to parse access token response");

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

  }

  @GetMapping("/kakao/login") // 최종 카카오 소셜 로그인 API 설계
  public ResponseEntity<Map<String, Object>> getKakaoLogin(@RequestHeader("Authorization") String authorizationHeader) {
    Map<String, Object> response = new HashMap<>();

    System.out.println(authorizationHeader);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  

}
