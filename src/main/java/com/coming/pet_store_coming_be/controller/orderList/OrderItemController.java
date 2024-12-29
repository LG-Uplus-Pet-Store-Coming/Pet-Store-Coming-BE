package com.coming.pet_store_coming_be.controller.orderList;

import com.coming.pet_store_coming_be.dto.orderList.OrderItemDTO;
import com.coming.pet_store_coming_be.service.orderList.OrderItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public String createOrderItems(@RequestBody List<OrderItemDTO> orderItems) {
    for (OrderItemDTO orderItem : orderItems) {
        orderItemService.createOrderItem(orderItem);
    }
    return "Order items created successfully.";
}
}