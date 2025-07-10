package com.projectsky.reactiveorders.controller;

import com.projectsky.reactiveorders.dto.OrderCreateDto;
import com.projectsky.reactiveorders.model.Order;
import com.projectsky.reactiveorders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Flux<Order> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Order> getOrderById(
            @PathVariable Long id
    ) {
        return orderService.getById(id);
    }

    @PostMapping
    public Mono<Order> createOrder(
            @RequestBody OrderCreateDto dto
    ) {
        return orderService.saveAndNotify(dto);

    }

    @GetMapping("/status")
    public Flux<Order> findByStatus(
            @RequestParam String status
    ) {
        return orderService.findByStatus(status);
    }
}
