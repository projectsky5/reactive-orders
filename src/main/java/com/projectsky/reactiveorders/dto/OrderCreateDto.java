package com.projectsky.reactiveorders.dto;

public record OrderCreateDto(
        Long productId,
        Integer quantity
) {
}

