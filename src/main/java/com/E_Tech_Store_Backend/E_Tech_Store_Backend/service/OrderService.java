package com.E_Tech_Store_Backend.E_Tech_Store_Backend.service;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.*;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.OrderItemRepository;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.OrderRepository;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final UserProfileService userProfileService;
    private final PaymentService paymentService; // Inject PaymentService

    @Transactional
    public Order placeOrder(String userEmail, String paymentIntentId) throws StripeException {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        if (!paymentIntent.getStatus().equals("succeeded")) {
            throw new RuntimeException("Payment not successful");
        }

        Cart cart = user.getCart();
        if (cart == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot place an order.");
        }

        UserProfile userProfile = userProfileService.getUserProfileByUser(user);
        if (userProfile == null || userProfile.getAddress() == null || userProfile.getAddress().isEmpty()) {
            throw new RuntimeException("User profile address is missing. Please update your profile.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setStatus("PAID"); // Initial status
        order.setShippingAddress(userProfile.getAddress() + ", " + userProfile.getCity() + ", " + userProfile.getState() + " " + userProfile.getZipCode() + ", " + userProfile.getCountry());
        order.setTotalAmount(paymentIntent.getAmount() / 100.0);

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice()); // Assuming Product has a getPrice() method
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        // Clear the cart after placing the order
        cartService.deleteCartById(cart.getId());

        return savedOrder;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }

    public List<Order> getOrdersByUserId(Integer userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getOrdersByUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return orderRepository.findByUserId(user.getId());
    }

    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
}
