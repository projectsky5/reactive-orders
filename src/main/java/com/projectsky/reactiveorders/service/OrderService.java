package com.projectsky.reactiveorders.service;

import com.projectsky.reactiveorders.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Order> getById(Long id);

    Flux<Order> getAll();

    void saveAndNotify(Order order);

    Flux<Order> findByStatus(String status);
}
