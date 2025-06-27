package com.E_Tech_Store_Backend.E_Tech_Store_Backend.mapper;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.ProductDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Product;

public interface ProductMapper {
    ProductDto ToProductDto(Product product);
    Product ToProductEntity(ProductDto productDto);
}
