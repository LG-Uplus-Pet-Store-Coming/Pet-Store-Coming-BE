package com.coming.pet_store_coming_be.dao.orderList;

import com.coming.pet_store_coming_be.dto.orderList.OrderListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderListDAO {
    // 유저 ID로 모든 주문 항목 조회
    List<OrderListDTO> findAllOrderItemsByUserId(@Param("userId") String userId);

    // 주문 항목 상태 업데이트 메서드
    void updateOrderItemStatus(@Param("orderItemId") String orderItemId, @Param("status") String status);

    // orderId로 paymentKey 조회
    @Select("SELECT payment_key FROM order_table WHERE order_id = #{orderId}")
    String findPaymentKeyByOrderId(@Param("orderId") String orderId);
}

// 조회 및 취소