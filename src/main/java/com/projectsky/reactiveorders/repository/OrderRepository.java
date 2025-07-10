package com.projectsky.reactiveorders.repository;

import com.projectsky.reactiveorders.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {

    Flux<Order> findByStatus(String status);
}
