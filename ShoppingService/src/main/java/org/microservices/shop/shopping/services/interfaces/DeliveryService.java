package org.microservices.shop.shopping.services.interfaces;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface DeliveryService {

    Mono<String> sendDeliveryRequest(UUID orderUuid);
}
