package com.E_Tech_Store_Backend.E_Tech_Store_Backend.service;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Product;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.RemovedProduct;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.repository.RemovedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemovedProductService {

    private final RemovedProductRepository removedProductRepository;

    public void saveRemovedProduct(Product product, String reason) {
        RemovedProduct removedProduct = RemovedProduct.builder()
                .product(product)
                .reason(reason)
                .build();
        removedProductRepository.save(removedProduct);
    }
}
