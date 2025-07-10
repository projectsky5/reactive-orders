package com.projectsky.reactiveorders.service;

import com.projectsky.reactiveorders.exception.OrderNotFoundException;
import com.projectsky.reactiveorders.model.Order;
import com.projectsky.reactiveorders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public Mono<Order> getById(Long id) {
        return orderRepository.findById(id)
                .switchIfEmpty(Mono.error(new OrderNotFoundException("Order not found")));
    }

    @Override
    public Flux<Order> getAll() {
        return orderRepository.findAll()
                .limitRate(50);
    }

    @Override
    public void saveAndNotify(Order order) {
        orderRepository.save(order)
                .doOnSuccess(savedOrder -> { // побочный эффект при успешной операции + создается новый Publisher
                    log.info("Новый заказ сохранен: {}", savedOrder);
                }).subscribe(); // Явная подписка с побочным эффектом на новый Publisher, можно еще в контроллере
    }

    @Override
    public Flux<Order> findByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}
