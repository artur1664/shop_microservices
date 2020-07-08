package org.microservices.shop.shopping.services;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.extern.slf4j.Slf4j;
import org.microservices.shop.shopping.mappers.OrderMapper;
import org.microservices.shop.shopping.model.Order;
import org.microservices.shop.shopping.model.dto.DeliveryDto;
import org.microservices.shop.shopping.repostitory.OrderRepository;
import org.microservices.shop.shopping.services.interfaces.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final WebClient client;
    private final OrderRepository orderRepository;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final OrderMapper mapper = OrderMapper.INSTANCE;

    @Autowired
    public DeliveryServiceImpl(WebClient client, OrderRepository orderRepository, CircuitBreakerRegistry circuitBreakerRegistry) {
        this.client = client;
        this.orderRepository = orderRepository;
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    /**
     * retrieve - gets response body
     * exchange - returns ClientResponse which contains status and headers!
     */
    @Override
    public Mono<String> sendDeliveryRequest(UUID orderUuid) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("DELIVERY_SERVICE_BREAKER");

        Mono<Order> order = orderRepository.findByOrderUuid(orderUuid);
        log.info("***************************** just before request");
        Mono<String> result = client.post()
                .uri("/api/v1/delivery")
                .body(Mono.just(order.map(mapper::orderToDto)), DeliveryDto.class)
                .retrieve()// other option is exchange
                .bodyToMono(String.class)
                .transform(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(error -> fallbackMethod(error, "do something!"));
        log.info("***************************** just after request");
        return result;
    }

    private Mono<String> fallbackMethod(Throwable error, String msg) {
        log.error("something went wrong!", error);
        log.debug("message for developer {}", msg);
        log.debug("do some actions from fallback method!");
        return Mono.just("failed!");
    }
}
