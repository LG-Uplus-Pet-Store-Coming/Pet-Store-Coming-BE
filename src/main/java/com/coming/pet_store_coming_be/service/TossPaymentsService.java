package com.coming.pet_store_coming_be.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;
import java.util.Map;

@Service
public class TossPaymentsService {
    
    @Value("${toss.payments.base-url}")
    private String baseUrl;
    
    @Value("${toss.payments.secret-key}")
    private String secretKey;

    private final RestTemplate restTemplate;

    public TossPaymentsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

      // 결제 승인 메서드
      public ResponseEntity<String> approvePayment(String paymentKey, String orderId, int amount) {
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_JSON);
          headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()));

          // Toss Payments 승인 요청의 body에 필요한 정보
          Map<String, Object> body = Map.of(
                  "paymentKey", paymentKey,
                  "orderId", orderId,
                  "amount", amount);

          HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
          String url = baseUrl + "/payments/confirm";

          return restTemplate.postForEntity(url, request, String.class);
      }
    
    // 결제 조회 메서드 추가
    public ResponseEntity<String> getPayment(String paymentKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()));

        HttpEntity<String> request = new HttpEntity<>(headers);
        String url = baseUrl + "/payments/" + paymentKey;

        return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
    }

    // 결제 취소 메서드
    public ResponseEntity<String> cancelPayment(String paymentKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()));
        
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.postForEntity(baseUrl + "/payments/" + paymentKey + "/cancel", request, String.class);
    }
}