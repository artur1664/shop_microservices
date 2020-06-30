package org.microservices.shop.shopping.controllers;

import lombok.extern.slf4j.Slf4j;
import org.microservices.shop.shopping.model.dto.OrderDto;
import org.microservices.shop.shopping.services.OrderService;
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

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Mono<ResponseEntity<OrderDto>> addNewOrder(@RequestBody OrderDto orderDto) {
        return orderService.addNewOrder(orderDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    @GetMapping
    public Mono<ResponseEntity<OrderDto>> getOne(@RequestParam UUID orderUuid) {
        return orderService.getOne(orderUuid).map(ResponseEntity::ok).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
