package com.projectsky.reactiveorders.repository;

import com.projectsky.reactiveorders.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    Flux<Product> findByNameContainsIgnoreCase(String name);
}
