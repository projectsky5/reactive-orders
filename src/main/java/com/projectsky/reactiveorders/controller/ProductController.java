package com.projectsky.reactiveorders.controller;

import com.projectsky.reactiveorders.dto.ProductCreateDto;
import com.projectsky.reactiveorders.model.Product;
import com.projectsky.reactiveorders.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(
            @PathVariable Long id
    ) {
        return productService.getById(id);
    }

    @PostMapping
    public Mono<Product> createProduct(
            @RequestBody ProductCreateDto dto
    ) {
        return productService.save(dto);
    }

    // Поиск по имени
    @GetMapping("/search")
    public Flux<Product> findByName(
            @RequestParam String name
    ) {
        return productService.findByName(name);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> streamNewProducts() {
        return productService.getHotNewProducts();
    }
}
