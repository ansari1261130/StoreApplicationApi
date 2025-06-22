package com.springboot.StoreApplicationApi.mappers;

import com.springboot.StoreApplicationApi.dtos.ProductDto;
import com.springboot.StoreApplicationApi.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);

}
