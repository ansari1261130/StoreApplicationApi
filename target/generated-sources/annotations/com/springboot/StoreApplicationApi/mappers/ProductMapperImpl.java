package com.springboot.StoreApplicationApi.mappers;

import com.springboot.StoreApplicationApi.dtos.product.ProductDto;
import com.springboot.StoreApplicationApi.dtos.product.RegisterProductRequest;
import com.springboot.StoreApplicationApi.entities.Category;
import com.springboot.StoreApplicationApi.entities.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T13:22:19+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setCategoryId( productCategoryId( product ) );
        if ( product.getId() != null ) {
            productDto.setId( product.getId() );
        }
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );
        productDto.setDescription( product.getDescription() );

        return productDto;
    }

    @Override
    public Product toEntity(RegisterProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( request.getName() );
        product.description( request.getDescription() );
        product.price( request.getPrice() );

        return product.build();
    }

    @Override
    public void update(RegisterProductRequest request, Product product) {
        if ( request == null ) {
            return;
        }

        product.setName( request.getName() );
        product.setDescription( request.getDescription() );
        product.setPrice( request.getPrice() );
    }

    private Byte productCategoryId(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }
}
