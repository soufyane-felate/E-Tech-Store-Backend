package com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto;

import lombok.Data;

@Data
public class AddToCartRequest {
    private Long productId;
    private int quantity;
}
