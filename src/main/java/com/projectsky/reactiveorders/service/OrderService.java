package com.projectsky.reactiveorders.service;

import com.projectsky.reactiveorders.dto.OrderCreateDto;
import com.projectsky.reactiveorders.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Order> getById(Long id);

    Flux<Order> getAll();

    Mono<Order> saveAndNotify(OrderCreateDto dto);

    Flux<Order> findByStatus(String status);
}
