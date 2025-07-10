package com.projectsky.reactiveorders.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    private Long productId;

    private Integer quantity;

    private String status;

    private LocalDateTime createdAt;
}
