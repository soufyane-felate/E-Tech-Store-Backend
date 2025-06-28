package com.E_Tech_Store_Backend.E_Tech_Store_Backend.mapper;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.dto.ProductDto;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring" ,unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ProductMapper {
    ProductDto ToProductDto(Product product);
    Product ToProductEntity(ProductDto productDto);
}


