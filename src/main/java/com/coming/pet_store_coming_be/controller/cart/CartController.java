package com.coming.pet_store_coming_be.controller.cart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coming.pet_store_coming_be.dto.cart.CartDTO;
import com.coming.pet_store_coming_be.service.cart.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
  
  @Autowired
  CartService cartService;

  @PostMapping("/append")
  public ResponseEntity<Map<String, Object>> insertCartItemController(@RequestBody CartDTO cart) {
    Map<String, Object> response = new HashMap<>();

    try {
      // 이미 추가된 상품일 경우
      if(cartService.isDuplicateProductInCartService(cart.getId(), cart.getProductId())) {
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("success", false);
        response.put("message", "This item is already in your cart.");
        response.put("errorCode", "CART_ITEM_ALREADY_EXISTS");

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
      }

      // 추가된 상품이 아닐 경우 -> 상품 등록
      cartService.insertCartItemService(cart);

      response.put("status", HttpStatus.CREATED.value());
      response.put("success", true);

      return new ResponseEntity<>(response, HttpStatus.CREATED);
      
    } catch (Exception e) {
      e.printStackTrace();

      response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.put("success", false);
      response.put("message", "Failed to create Canidae.");
      response.put("errorCode", "INTERNAL_SERVER_ERROR");

      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
