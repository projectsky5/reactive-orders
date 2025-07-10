package com.projectsky.reactiveorders.service;

import com.projectsky.reactiveorders.dto.ProductCreateDto;
import com.projectsky.reactiveorders.exception.ProductNotFoundException;
import com.projectsky.reactiveorders.model.Product;
import com.projectsky.reactiveorders.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // Hot publisher
    private final Sinks.Many<Product> hotNewProducts =
            Sinks.many().multicast().onBackpressureBuffer();

    // Mono - возвращает 0 - 1 элементов.
    @Override
    public Mono<Product> getById(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id.toString())));
    }

    // Flux - возвращает 0 - N элементов
    // limitRate - Применение backpressure (ограничение скорости запросов из базы)
    @Override
    public Flux<Product> getAll() {
        return productRepository.findAll()
                .limitRate(100); // Не загружать более 100 одновременно
    }

    @Override
    @CachePut(value = "productsByName", key = "#dto.name")
    public Mono<Product> save(ProductCreateDto dto) {
        Product product = Product.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .build();

        return productRepository.save(product)
                .doOnSuccess(savedProduct -> {
                    log.info("Создан продукт={}", savedProduct.getName());
                    hotNewProducts.tryEmitNext(savedProduct); //Отправка в hot stream
                });
    }

    @Override
    @Cacheable(value = "productsByName", key = "#name")
    public Flux<Product> findByName(String name) {
        return productRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Flux<Product> getHotNewProducts() {
        return hotNewProducts.asFlux();
    }


}
