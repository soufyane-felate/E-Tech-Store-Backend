package com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private Long id;
    private List<CartItemDto> items;
}
