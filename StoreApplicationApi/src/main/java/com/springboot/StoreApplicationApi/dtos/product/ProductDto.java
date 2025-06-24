package com.springboot.StoreApplicationApi.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Byte categoryId;

}
