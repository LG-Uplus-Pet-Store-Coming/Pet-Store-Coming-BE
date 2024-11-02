package com.coming.pet_store_coming_be.service.orderList;

import com.coming.pet_store_coming_be.dto.orderList.OrderListDTO;
import com.coming.pet_store_coming_be.dao.orderList.OrderListDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class OrderListService {

    private final OrderListDAO orderListDAO;
    private final RestTemplate restTemplate;

    // Toss Payments 설정 정보
    @Value("${toss.payments.base-url}")
    private String baseUrl;

    @Value("${toss.payments.secret-key}")
    private String secretKey;

    public OrderListService(OrderListDAO orderListDAO, RestTemplate restTemplate) {
        this.orderListDAO = orderListDAO;
        this.restTemplate = restTemplate;
    }

    // 유저 ID로 모든 주문 항목 조회
    public List<OrderListDTO> getAllOrderItemsByUserId(String userId) {
        return orderListDAO.findAllOrderItemsByUserId(userId);
    }

    // 주문 항목 부분 취소 처리
    public boolean cancelOrderItem(String orderItemId, String orderId, int cancelAmount) {
        // orderId를 이용해 paymentKey 조회
        String paymentKey = orderListDAO.findPaymentKeyByOrderId(orderId);

        if (paymentKey == null) {
            throw new IllegalArgumentException("해당 orderId에 대한 paymentKey를 찾을 수 없습니다.");
        }

        // Toss Payments에 부분 취소 요청
        boolean isCancelled = requestPartialCancelFromToss(paymentKey, cancelAmount);

        // 부분 취소가 성공하면 DB의 상태를 '주문취소'로 업데이트
        if (isCancelled) {
            orderListDAO.updateOrderItemStatus(orderItemId, "주문취소");
            return true;
        }
        return false;
    }

    // Toss Payments API 부분 취소 요청
   private boolean requestPartialCancelFromToss(String paymentKey, int cancelAmount) {
    try {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()));
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        // 필수 파라미터들: cancelAmount와 cancelReason 추가
        Map<String, Object> body = Map.of(
            "cancelAmount", cancelAmount,
            "cancelReason", "부분 취소 요청"
        );
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        String url = baseUrl + "/payments/" + paymentKey + "/cancel";
        System.out.println("Request URL: " + url);  // 디버깅용 URL 출력

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response.getStatusCode().is2xxSuccessful();
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}