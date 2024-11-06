package com.coming.pet_store_coming_be.service.orderList;

import com.coming.pet_store_coming_be.dto.orderList.OrderItemDTO;
import com.coming.pet_store_coming_be.dao.orderList.OrderItemDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderItemService {

    private final OrderItemDAO orderItemDAO;

    @Autowired
    public OrderItemService(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    public void createOrderItem(OrderItemDTO orderItem) {
        orderItemDAO.insertOrderItem(orderItem);
    }
}