package com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Cart;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByCart(Cart cart);

}