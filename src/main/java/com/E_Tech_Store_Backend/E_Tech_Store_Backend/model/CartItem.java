package com.E_Tech_Store_Backend.E_Tech_Store_Backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private int quantity;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;

}
