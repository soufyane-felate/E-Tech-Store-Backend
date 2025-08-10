package com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private int quantity;
    private ProductDto product;
}
