package com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}