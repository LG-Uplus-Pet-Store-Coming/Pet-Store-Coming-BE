package com.coming.pet_store_coming_be.dto.orderList;

public class OrderItemDTO {
    private Long orderItem;
    private String orderId;
    private String productId;
    private int quantity;
    private String status="주문완료";
    private String createdAt;
    private String updatedAt;
    public Long getOrderItem() {
        return orderItem;
    }
    public void setOrderItem(Long orderItem) {
        this.orderItem = orderItem;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
