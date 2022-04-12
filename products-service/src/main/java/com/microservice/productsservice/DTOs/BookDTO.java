package com.microservice.productsservice.DTOs;

import com.microservice.productsservice.Entities.Author;
import com.microservice.productsservice.Entities.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String name;
    private Set<String> authors;
    private Set<String> categories;
    private String sku;
    private String sumary;
    private String img;
    private BigDecimal price;
    private long stock;
}
