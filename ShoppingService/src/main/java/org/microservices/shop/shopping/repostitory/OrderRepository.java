package org.microservices.shop.shopping.repostitory;

import org.microservices.shop.shopping.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, Long> {
}
