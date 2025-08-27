package com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto;

import lombok.Data;

@Data
public class PlaceOrderRequest {
    private String paymentIntentId;
    private String shippingAddress;
}
