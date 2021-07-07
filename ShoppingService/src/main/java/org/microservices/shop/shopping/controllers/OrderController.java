package org.microservices.shop.shopping.controllers;

import lombok.extern.slf4j.Slf4j;
import org.microservices.shop.shopping.model.dto.OrderDto;
import org.microservices.shop.shopping.services.interfaces.DeliveryService;
import org.microservices.shop.shopping.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    private final DeliveryService deliveryService;

    @Autowired
    public OrderController(OrderService orderService, DeliveryService deliveryService) {
        this.orderService = orderService;
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public Mono<ResponseEntity<UUID>> addNewOrder(@RequestBody OrderDto orderDto) {
        return orderService.addNewOrder(orderDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    @GetMapping
    public Mono<ResponseEntity<OrderDto>> getOne(@RequestParam UUID orderUuid) {
        return orderService.getOne(orderUuid).map(ResponseEntity::ok).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/deliver")
    public Mono<ResponseEntity<String>> startDeliveryProcess(@RequestParam UUID orderUuid) {
        return deliveryService.sendDeliveryRequest(orderUuid)
                .map(s -> ResponseEntity.status(HttpStatus.OK).body(s));
    }
}
