package com.springboot.StoreApplicationApi.mappers;

import com.springboot.StoreApplicationApi.dtos.product.RegisterProductRequest;
import com.springboot.StoreApplicationApi.dtos.product.ProductDto;
import com.springboot.StoreApplicationApi.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);
    Product toEntity(RegisterProductRequest request);

    @Mapping(target = "id",ignore = true)
    void update(RegisterProductRequest request,@MappingTarget Product product);
}
