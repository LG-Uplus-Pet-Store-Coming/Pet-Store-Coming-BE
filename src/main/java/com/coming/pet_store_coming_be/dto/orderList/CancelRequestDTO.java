package com.coming.pet_store_coming_be.dto.orderList;

public class CancelRequestDTO {
    private String orderItemId;
    private String orderId;
    private int cancelAmount;
    public String getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public int getCancelAmount() {
        return cancelAmount;
    }
    public void setCancelAmount(int cancelAmount) {
        this.cancelAmount = cancelAmount;
    }

    // Getters and Setters
}