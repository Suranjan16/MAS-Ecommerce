package com.suranjan.mas.cart.dto;

public class CartItemResponse {

    private String productName;
    private Double price;
    private Integer quantity;

    public CartItemResponse(
            String productName,
            Double price,
            Integer quantity
    ) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}