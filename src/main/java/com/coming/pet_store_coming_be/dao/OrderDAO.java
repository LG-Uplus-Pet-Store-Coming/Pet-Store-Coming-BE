package com.coming.pet_store_coming_be.dao;

import com.coming.pet_store_coming_be.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDAO {
    void insertOrder(OrderDTO order);
}
