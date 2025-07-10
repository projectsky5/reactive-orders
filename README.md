Reactive Orders

Реактивное Spring Boot приложение, реализующее CRUD для продуктов и заказов на реактивном стеке.

---

О проекте

Приложение - это сервис управления продуктами и заказами с такими возможностями:

	•	CRUD-операции для продуктов
	•	CRUD-операции для заказов
	•	hot поток новых продуктов (Sinks + Flux)
	•	Кэширование результатов поиска продуктов по имени
	•	Обработка backpressure для предотвращения перегрузки subscriber'ов
	•	Корректная обработка ошибок и fallback через switchIfEmpty
	•	Побочные эффекты в паблишерах (doOnSuccess)

Используется Spring WebFlux - реактивная неблокирующая альтернатива Spring MVC.

---

Ключевые моменты

Реактивный CRUD

	•	Все эндпоинты возвращают либо Mono<T> (0 - 1), либо Flux<T> (0 - n), обеспечивая неблокирующий поток данных.
	•	Данные запрашиваются лениво — только при подписке (в контроллерах Spring WebFlux сам подписывается).

Hot Publisher

	•	Новые продукты сразу публикуются в hot поток через Sinks.Many<Product> и доступны как Flux<Product>.

Sinks & Flux

	•	Sinks — эмиттер событий.
	•	Подписчики на hot Flux видят события в реальном времени, по мере их поступления.

Backpressure

	•	Потоки используют limitRate(n) для ограничения количества обрабатываемых элементов за раз.

Кэширование

	•	Результаты поиска продуктов по имени кэшируются с помощью Spring Cache (@Cacheable, @CachePut).

Побочные эффекты

	•	Действия логирования выполняются как побочные эффекты (doOnSuccess) без блокировок и влияния на основную логику.

---

Стек технологий

	•	Java 21
	•	Spring Boot 3.5.3
	•	Spring WebFlux
	•	Spring Data R2DBC
	•	Spring Cache (in-memory)
	•	Lombok
	•	Flyway 
	•	PostgreSQL
	•	docker-compose (контейнер PostgreSQL)

---

Инструкция для запуска
```
docker-compose up -d
./mvnw clean spring-boot:run
```

---
Эндпоинты:

<img width="1454" height="626" alt="image" src="https://github.com/user-attachments/assets/6a240f00-8dc7-4685-ac6a-8e0cfbbcf9a5" />
