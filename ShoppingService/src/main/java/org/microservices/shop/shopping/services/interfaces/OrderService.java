package org.microservices.shop.shopping.services.interfaces;

import org.microservices.shop.shopping.model.dto.OrderDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface OrderService {

    Mono<UUID> addNewOrder(OrderDto orderDto);

    Mono<OrderDto> getOne(UUID orderUuid);

}
