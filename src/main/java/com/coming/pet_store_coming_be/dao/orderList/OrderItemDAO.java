package com.coming.pet_store_coming_be.dao.orderList;

import com.coming.pet_store_coming_be.dto.orderList.OrderItemDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemDAO {
    void insertOrderItem(OrderItemDTO orderItem);
}
