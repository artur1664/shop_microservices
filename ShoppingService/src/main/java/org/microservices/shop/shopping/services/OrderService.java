package org.microservices.shop.shopping.services;

import org.microservices.shop.shopping.model.dto.OrderDto;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<OrderDto> addNewOrder(OrderDto orderDto);
}
