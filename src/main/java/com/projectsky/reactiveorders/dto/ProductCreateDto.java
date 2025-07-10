package com.projectsky.reactiveorders.dto;

import java.math.BigDecimal;

public record ProductCreateDto(
        String name,
        String description,
        BigDecimal price
) {
}
