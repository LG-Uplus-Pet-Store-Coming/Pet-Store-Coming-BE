package com.coming.pet_store_coming_be.controller.orderList;

import com.coming.pet_store_coming_be.dto.orderList.CancelRequestDTO;
import com.coming.pet_store_coming_be.dto.orderList.OrderListDTO;
import com.coming.pet_store_coming_be.service.orderList.OrderListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderListController {

    private final OrderListService orderListService;

    public OrderListController(OrderListService orderListService) {
        this.orderListService = orderListService;
    }

    // 유저 ID로 모든 주문 항목 조회
    @GetMapping("/user/{userId}/items")
    public List<OrderListDTO> getAllOrderItemsByUserId(@PathVariable String userId) {
        return orderListService.getAllOrderItemsByUserId(userId);
    }

    // 주문 항목 부분 취소 API
   @PostMapping("/cancel-item")
    public ResponseEntity<String> cancelOrderItem(@RequestBody CancelRequestDTO cancelRequest) {
    boolean isCancelled = orderListService.cancelOrderItem(
            cancelRequest.getOrderItemId(),
            cancelRequest.getOrderId(),
            cancelRequest.getCancelAmount()
    );

    if (isCancelled) {
        return ResponseEntity.ok("주문 항목이 성공적으로 취소되었습니다.");
    } else {
        return ResponseEntity.status(500).body("주문 취소에 실패했습니다.");
    }
}
}