package com.E_Tech_Store_Backend.E_Tech_Store_Backend.controller;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.ProductDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("api/product")

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
    @GetMapping
    public List<ProductDto>getAllProduct()
    {
        return productService.getAllProduct();
    }
    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id,@RequestBody ProductDto productDto){
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
         productService.deleteProduct(id);
    }


}
