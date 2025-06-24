package com.springboot.StoreApplicationApi.dtos.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;
}
