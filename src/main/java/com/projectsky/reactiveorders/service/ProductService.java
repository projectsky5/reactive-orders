package com.projectsky.reactiveorders.service;

import com.projectsky.reactiveorders.dto.ProductCreateDto;
import com.projectsky.reactiveorders.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> getById(Long id);

    Flux<Product> getAll();

    Mono<Product> save(ProductCreateDto dto);

    Flux<Product> findByName(String name);

    Flux<Product> getHotNewProducts();
}
