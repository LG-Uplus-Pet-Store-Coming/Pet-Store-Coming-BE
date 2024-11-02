package com.coming.pet_store_coming_be.service;

import com.coming.pet_store_coming_be.dao.OrderDAO;
import com.coming.pet_store_coming_be.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Map;

@Service
public class TossPaymentsService {
    
    @Value("${toss.payments.base-url}")
    private String baseUrl;
    
    @Value("${toss.payments.secret-key}")
    private String secretKey;

    private final RestTemplate restTemplate;
    private final OrderDAO orderDAO;

    public TossPaymentsService(RestTemplate restTemplate, OrderDAO orderDAO) {
        this.restTemplate = restTemplate;
        this.orderDAO = orderDAO;
    }

    // 결제 승인 메서드
    public ResponseEntity<String> approvePayment(String paymentKey, String orderId, int amount, String userId,
                                             String receiverName, String deliveryAddress, String phoneNumber) {
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

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // 결제 승인 성공 시 OrderDTO 생성 후 DB에 저장
            OrderDTO order = new OrderDTO();
            order.setOrderId(orderId);
            order.setUserId(userId);
            order.setPaymentKey(paymentKey);
            order.setTotalAmount(BigDecimal.valueOf(amount));
            order.setReceiverName(receiverName);
            order.setDeliveryAddress(deliveryAddress);
            order.setPhoneNumber(phoneNumber);
            orderDAO.insertOrder(order);  // DB에 저장

            return ResponseEntity.ok("결제가 성공적으로 승인되었습니다.");
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("결제 승인에 실패했습니다.");
        }
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
    
    // 거래 내역 조회
    public ResponseEntity<String> getTransactions(String startDate, String endDate, Integer limit) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = baseUrl + "/transactions?startDate=" + startDate + "&endDate=" + endDate + "&limit=" + limit;
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
    }
}