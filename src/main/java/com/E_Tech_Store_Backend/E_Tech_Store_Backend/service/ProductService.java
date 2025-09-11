package com.E_Tech_Store_Backend.E_Tech_Store_Backend.service;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.ProductDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.mapper.ProductMapper;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Product;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final RemovedProductService removedProductService;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository, RemovedProductService removedProductService) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.removedProductService = removedProductService;
    }

    public ProductDto addProduct(ProductDto productDto){
        return productMapper
                .ToProductDto(productRepository
                        .save(productMapper
                                .ToProductEntity(productDto)));
    }

    public List<ProductDto>getAllProduct()
    {
        return productRepository
                .findAll()
                .stream()
                .map(product -> productMapper.ToProductDto(product))
                .toList();
    }
//
  public ProductDto getproductById(Long id){
        return productMapper.ToProductDto(productRepository.findById(id).orElse(null));
  }

    public ProductDto updateProduct(Long id , ProductDto productDto)
    {
        Product product = productRepository.findById(id).get();
        product.setName(productDto.getName());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setCategorie(productDto.getCategorie());
        product.setPrice(productDto.getPrice());
        product.setEtat(productDto.getEtat());
        return productMapper.ToProductDto(productRepository.save(productMapper.ToProductEntity(productDto)));
    }
    public void removeProduct(Long id, String reason) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        removedProductService.saveRemovedProduct(product, reason);
        productRepository.deleteById(id);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public long countProducts() {
        return productRepository.count();
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductDto incrementClick(Long id) {
         Product product=productRepository.findById(id).get();
         product.setCountClick(product.getCountClick()+1);
         return productMapper.ToProductDto(productRepository.save(product));
    }
}
