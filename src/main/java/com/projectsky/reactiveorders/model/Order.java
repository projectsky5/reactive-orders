package com.projectsky.reactiveorders.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(name = "orders")
@Builder
public class Order {

    @Id
    private Long id;

    private Long productId;

    private Integer quantity;

    private String status;

    private LocalDateTime createdAt;
}
