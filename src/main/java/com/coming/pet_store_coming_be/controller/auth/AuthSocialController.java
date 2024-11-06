package com.coming.pet_store_coming_be.controller.auth;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.coming.pet_store_coming_be.config.AESConfig;
import com.coming.pet_store_coming_be.dto.UserDTO;
import com.coming.pet_store_coming_be.security.AESUtil;
import com.coming.pet_store_coming_be.security.TokenProvider;
import com.coming.pet_store_coming_be.service.auth.AuthService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/auth/social")
public class AuthSocialController {
  
  @Autowired
  AuthService authService;

  @Autowired
  AESUtil aesUtil;
  
  @Autowired
  AESConfig aesConfig;

  @Autowired
  TokenProvider tokenProvider;

  RestTemplate restTemplate = new RestTemplate();

  @Value("${kakao.client-id}")
  private String clientId;

  @Value("${kakao.redirect-url}")
  private String redirectUri;

  @Value("${kakao.token-url}")
  private String kakaoTokenUrl;

  @PostMapping("/kakao/request/token") // 카카오 소셜 로그인을 위한 AccessToken 발급 여부 API
  public ResponseEntity<Map<String, Object>> requestKakaoToken(@RequestParam("code") String code) {
    
    Map<String, Object> response = new HashMap<>();

    try {
      
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
      response.put("message", "Failed to request access token from Kakao");
      response.put("errorCode", "KAKAO_TOKEN_REQUEST_ERROR");

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

  }

  @PostMapping("/kakao/login") // 최종 카카오 소셜 로그인 API 설계
  public ResponseEntity<Map<String, Object>> getKakaoLogin(@RequestParam("device_id") String deviceId, @RequestHeader("Authorization") String authorizationHeader) {
    Map<String, Object> response = new HashMap<>();

    try {

      // 카카오 사용자 정보 가져오기
      String requestKakaoUserInfoUrl = "https://kapi.kakao.com/v2/user/me";

      // 카카오 사용자 정보 가져오기 요청 헤더 설정
      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", authorizationHeader);

      // 카카오 사용자 정보 가져오기
      Map<String, Object> kakaoUserInfo = 
        restTemplate
          .exchange(requestKakaoUserInfoUrl, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<Map<String, Object>>() {})
          .getBody();


      Long kakaoId = (Long) kakaoUserInfo.get("id"); // 카카오 고유키 가져오기

      // 고유키 암호화
      String kakaoIdAsString = String.valueOf(kakaoId);
      String encryptKakaoId = aesUtil.encrypt(kakaoIdAsString);

      UserDTO socialUserInfo = authService.getSocialUserInfoService(encryptKakaoId);

      // 카카오 사용자 정보가 DB에 없는 경우 -> 회원가입 진행
      if(socialUserInfo == null) {
        response.put("success", false);
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("message", "User not found. Registration is required to proceed.");
        response.put("errorCode", "KAKAO_USER_NOT_FOUND");
        response.put("id", encryptKakaoId);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      }

      // 카카오 사용자 정보가 DB에 있는 경우 -> 로그인 진행
      String token = tokenProvider.createToken(socialUserInfo);
      String refreshToken = tokenProvider.createRefreshToken(socialUserInfo.getId(), deviceId);
    
      authService.invalidateAndSaveNewRefreshToken(socialUserInfo.getId(), refreshToken, deviceId); // 기존 토큰 무효화 및 새로운 디바이스 리프레시 토큰 저장

      // 토큰 생성 및 UserInfo refresh_token 및 is_active 갱신을 완료한 이후 응답 생성
      response.put("status", HttpStatus.OK.value());
      response.put("success", true);
      response.put("message", "Login successful.");
      response.put("token", token);
      response.put("refreshToken", refreshToken);
      response.put("expirationTime", tokenProvider.setTokenExpiry().getTime()); // 토큰 만료 시간 추가

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();

      response.put("success", false);
      response.put("status", HttpStatus.BAD_REQUEST.value());
      response.put("message", "Failed to process Kakao login");
      response.put("errorCode", "KAKAO_LOGIN_PROCESS_ERROR");

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
  }
  

}
