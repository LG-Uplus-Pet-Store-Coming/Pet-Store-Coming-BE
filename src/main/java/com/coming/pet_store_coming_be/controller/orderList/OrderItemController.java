package com.coming.pet_store_coming_be.controller.orderList;

import com.coming.pet_store_coming_be.dto.orderList.OrderItemDTO;
import com.coming.pet_store_coming_be.service.orderList.OrderItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public String createOrderItem(@RequestBody OrderItemDTO orderItem) {
        orderItemService.createOrderItem(orderItem);
        return "Order item created successfully.";
    }
}