package com.E_Tech_Store_Backend.E_Tech_Store_Backend.service;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Cart;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.CartItem;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Product;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.User;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.CartItemRepository;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.CartRepository;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.ProductRepository;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addProductToCart(String userEmail, Long productId, int quantity) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cartRepository.save(cart);
            user.setCart(cart);
            userRepository.save(user);
        }

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                cartItemRepository.save(item);
                return;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        cart.getItems().add(cartItem);
        cartRepository.save(cart);
    }

    public Cart getCart(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cartRepository.save(cart);
            user.setCart(cart);
            userRepository.save(user);
        }
        cart.getItems().size();
        return cart;
    }

//    public void updateCartItemQuantity(String userEmail, Long productId, int newQuantity) {
//        User user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        Cart cart = user.getCart();
//        if (cart == null) {
//            throw new RuntimeException("Cart not found for user");
//        }
//
//        CartItem targetItem = null;
//        for (CartItem item : cart.getItems()) {
//            if (item.getProduct().getId().equals(productId)) {
//                targetItem = item;
//                break;
//            }
//        }
//
//        if (targetItem == null) {
//            throw new RuntimeException("Product not found in cart");
//        }
//
//        if (newQuantity <= 0) {
//            cart.getItems().remove(targetItem);
//            cartItemRepository.delete(targetItem);
//        } else {
//            targetItem.setQuantity(newQuantity);
//            cartItemRepository.save(targetItem);
//        }
//        cartRepository.save(cart);
//    }
public void updateCartItemQuantity(String userEmail, Long productId, int newQuantity) {
    User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    Cart cart = user.getCart();
    if (cart == null) {
        throw new RuntimeException("Cart not found for user");
    }

    CartItem targetItem = cart.getItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Product not found in cart"));

    if (newQuantity <= 0) {
        cart.getItems().remove(targetItem);
        cartItemRepository.delete(targetItem);
    } else {
        targetItem.setQuantity(newQuantity);
        cartItemRepository.save(targetItem);
    }

    cartRepository.save(cart);
}

    public void deleteCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));

        cart.getItems().clear();
        cartRepository.save(cart);
    }
    public void deleteCartItem(String userEmail, Long productId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            throw new RuntimeException("Cart not found for user");
        }

        CartItem cartItemToRemove = null;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                cartItemToRemove = item;
                break;
            }
        }

        if (cartItemToRemove == null) {
            throw new RuntimeException("Product not found in cart");
        }

        cart.getItems().remove(cartItemToRemove);
        cartItemRepository.delete(cartItemToRemove);
        cartRepository.save(cart);
    }
}