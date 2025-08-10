package com.E_Tech_Store_Backend.E_Tech_Store_Backend.mapper;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.CartDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.CartItemDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Cart;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.CartItem;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CartMapper {

    @Autowired
    ProductMapper productMapper;

    public CartDto cartToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setItems(cartItemsToCartItemDtos(cart.getItems()));
        return cartDto;
    }

    public List<CartItemDto> cartItemsToCartItemDtos(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::cartItemToCartItemDto)
                .collect(Collectors.toList());
    }

    public CartItemDto cartItemToCartItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProduct(productMapper.ToProductDto(cartItem.getProduct()));
        return cartItemDto;
    }
}
