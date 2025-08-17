package com.E_Tech_Store_Backend.E_Tech_Store_Backend.controller;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.AddToCartRequest;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.CartDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.UpdateCartItemRequest;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.mapper.CartMapper;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Cart;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @PostMapping("/add")
    public ResponseEntity<Void> addProductToCart(Authentication authentication, @RequestBody AddToCartRequest request) {
        String userEmail = authentication.getName();
        cartService.addProductToCart(userEmail, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CartDto> getCart(Authentication authentication) {
        String userEmail = authentication.getName();
        Cart cart = cartService.getCart(userEmail);
        return ResponseEntity.ok(cartMapper.cartToCartDto(cart));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCartItemQuantity(Authentication authentication, @RequestBody UpdateCartItemRequest request) {
        String userEmail = authentication.getName();
        cartService.updateCartItemQuantity(userEmail, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCartById(@PathVariable Long cartId) {
        cartService.deleteCartById(cartId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<Void> deleteCartItem(Authentication authentication, @PathVariable Long productId) {
        String userEmail = authentication.getName();
        cartService.deleteCartItem(userEmail, productId);
        return ResponseEntity.noContent().build();
    }
}