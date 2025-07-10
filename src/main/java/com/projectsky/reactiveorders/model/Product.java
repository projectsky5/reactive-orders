package com.projectsky.reactiveorders.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "products")
public class Product {

    @Id
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDateTime createdAt;
}
