package org.microservices.shop.shopping.services;

import org.microservices.shop.shopping.model.dto.OrderDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface OrderService {

    Mono<OrderDto> addNewOrder(OrderDto orderDto);

    Mono<OrderDto> getOne(UUID orderUuid);

}
