package com.E_Tech_Store_Backend.E_Tech_Store_Backend.controller;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.RemoveProductDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Product;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.User;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.service.ProductService;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/users/count")
    public ResponseEntity<Long> countUsers() {
        return ResponseEntity.ok(userService.countUsers());
    }

    @GetMapping("/products/count")
    public ResponseEntity<Long> countProducts() {
        return ResponseEntity.ok(productService.countProducts());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long id, @RequestParam String reason) {
        productService.removeProduct(id, reason);
        return ResponseEntity.ok().build();
    }
}
