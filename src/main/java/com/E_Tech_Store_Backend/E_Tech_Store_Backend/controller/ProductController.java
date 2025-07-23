package com.E_Tech_Store_Backend.E_Tech_Store_Backend.controller;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.ProductDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.Categorie;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.enums.ETAT;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.service.FileStorageService;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("api/product")

public class ProductController {
    private final ProductService productService;
    private final FileStorageService fileStorageService;

    public ProductController(ProductService productService, FileStorageService fileStorageService) {
        this.productService = productService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public ProductDto addProduct(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("price") double price,
                                 @RequestParam("categorie") Categorie categorie,
                                 @RequestParam("etat") ETAT etat,
                                 @RequestParam("image") MultipartFile image){
        String imageName = fileStorageService.save(image);
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setPrice(price);
        productDto.setCategorie(categorie);
        productDto.setEtat(etat);
        productDto.setImage(imageName);
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
