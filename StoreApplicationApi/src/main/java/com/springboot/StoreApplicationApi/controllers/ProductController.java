package com.springboot.StoreApplicationApi.controllers;

import com.springboot.StoreApplicationApi.dtos.product.ProductDto;
import com.springboot.StoreApplicationApi.dtos.product.RegisterProductRequest;
import com.springboot.StoreApplicationApi.entities.Product;
import com.springboot.StoreApplicationApi.mappers.ProductMapper;
import com.springboot.StoreApplicationApi.repositories.CategoryRepository;
import com.springboot.StoreApplicationApi.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(name = "categoryId", required = false) Byte categoryId
    ) {
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }
        return products.stream().map(productMapper::toDto).toList();
    }


    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> registerProduct(
            @RequestBody RegisterProductRequest request,
            UriComponentsBuilder uriBuilder
            ) {
        var category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new IllegalArgumentException("Category not found"));
        if(category == null) {
            return ResponseEntity.badRequest().body(null);
        }
        var product = productMapper.toEntity(request);
        product.setCategory(category);
        productRepository.save(product);
        var productDto = productMapper.toDto(product);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name="id") Long id,
            @RequestBody RegisterProductRequest request
    ) {
        var category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new IllegalArgumentException("Category not found"));
        if(category == null) {
            return ResponseEntity.badRequest().body(null);
        }
        var product = productRepository.findById(id).orElse(null);
        if(product == null) return ResponseEntity.notFound().build();
        productMapper.update(request,product);
        product.setCategory(category);
        productRepository.save(product);
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable(name="id") Long id
    ) {
        var product = productRepository.findById(id).orElse(null);
        if(product == null) return ResponseEntity.notFound().build();
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }


}
