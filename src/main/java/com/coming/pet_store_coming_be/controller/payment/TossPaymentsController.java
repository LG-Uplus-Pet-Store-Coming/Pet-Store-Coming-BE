package com.coming.pet_store_coming_be.controller.payment;


import com.coming.pet_store_coming_be.service.TossPaymentsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/payments")
public class TossPaymentsController {
    private final TossPaymentsService tossPaymentsService;

    public TossPaymentsController(TossPaymentsService tossPaymentsService) {
        this.tossPaymentsService = tossPaymentsService;
    }

    // 결제 승인 엔드포인트
    @PostMapping("/approve")
    public ResponseEntity<String> approvePayment(@RequestBody Map<String, Object> paymentData) {
        String userId = (String) paymentData.get("userId");
        String paymentKey = (String) paymentData.get("paymentKey");
        String orderId = (String) paymentData.get("orderId");
        int amount = (Integer) paymentData.get("amount");

        ResponseEntity<String> response = tossPaymentsService.approvePayment(paymentKey, orderId, amount, userId);
        if (response.getStatusCode() == HttpStatus.OK) {
            // 결제 승인 성공 처리 - 데이터베이스에 결제 상태 기록 가능
            return ResponseEntity.ok("결제가 성공적으로 승인되었습니다.");
        } else {
            // 결제 승인 실패 처리
            return ResponseEntity.status(response.getStatusCode()).body("결제 승인에 실패했습니다.");
        }
    }

      // 결제 조회 엔드포인트
    @GetMapping("/get/{paymentKey}")
    public ResponseEntity<String> getPayment(@PathVariable String paymentKey) {
        try {
            ResponseEntity<String> response = tossPaymentsService.getPayment(paymentKey);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("결제 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 결제 취소 엔드포인트
    @PostMapping("/cancel/{paymentKey}")
    public ResponseEntity<String> cancelPayment(@PathVariable String paymentKey) {
        try {
            ResponseEntity<String> response = tossPaymentsService.cancelPayment(paymentKey);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("결제 취소 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 특정 기간 거래 내역 조회 엔드포인트
    @GetMapping("/transactions")
    public ResponseEntity<String> getTransactions(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(required = false, defaultValue = "60") Integer limit
            ) {
        try {
            return tossPaymentsService.getTransactions(startDate, endDate, limit);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("거래 내역 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}

