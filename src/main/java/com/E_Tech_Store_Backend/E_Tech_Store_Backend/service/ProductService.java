package com.E_Tech_Store_Backend.E_Tech_Store_Backend.service;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.ProductDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.mapper.ProductMapper;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public ProductService (ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository=productRepository;
        this.productMapper = productMapper;
    }

    public ProductDto addProduct(ProductDto productDto)
    {
        return productMapper
                .ToProductDto(productRepository.save(productMapper.ToProductEntity(productDto)));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(product -> productMapper.ToProductDto(product)).toList();
    }
}
