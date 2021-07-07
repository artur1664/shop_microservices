package org.microservices.shop.shopping.repostitory;

import org.microservices.shop.shopping.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, Long> {

    Mono<Order> findByOrderUuid(UUID orderUuid);
}
