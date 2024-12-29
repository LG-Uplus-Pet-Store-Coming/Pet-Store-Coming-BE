package com.coming.pet_store_coming_be.service.orderList;

import com.coming.pet_store_coming_be.dto.orderList.OrderItemDTO;
import com.coming.pet_store_coming_be.dao.orderList.OrderItemDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemDAO orderItemDAO;

    public OrderItemService(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    public void createOrderItem(OrderItemDTO orderItem) {
        orderItemDAO.insertOrderItem(orderItem);
    }

    public void createOrderItems(List<OrderItemDTO> orderItems) {
        for (OrderItemDTO orderItem : orderItems) {
            createOrderItem(orderItem); // 기존의 단일 항목 처리 메서드를 호출
        }
    }
}