package com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Integer userId);
}
