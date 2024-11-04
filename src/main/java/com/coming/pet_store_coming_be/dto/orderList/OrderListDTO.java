package com.coming.pet_store_coming_be.dto.orderList;

public class OrderListDTO {
    private String orderId;
    private String productId;
    private String productName;
    private String storeId;
    private int price;
    private int discountPrice;  // 할인 가격
    private int quantity;
    private String status;  // 상품별 상태 (주문완료, 주문취소, 주문 부분취소 등)
    private String thumbnailImageUrl;

    // 생성자
    public OrderListDTO(String orderId, String productId, String productName, String storeId, int price, int discountPrice, int quantity, String status, String thumbnailImageUrl) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.storeId = storeId;
        this.price = price;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
        this.status = status;
        this.thumbnailImageUrl = thumbnailImageUrl;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
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

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    // Getters and Setters
}