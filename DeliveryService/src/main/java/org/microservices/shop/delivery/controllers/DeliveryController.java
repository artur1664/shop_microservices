package org.microservices.shop.delivery.controllers;

import lombok.extern.slf4j.Slf4j;
import org.microservices.shop.delivery.model.dto.DeliveryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    @PostMapping
    Mono<ResponseEntity<String>> testCommunication(@RequestBody DeliveryDto deliveryDto) {
        log.info("new delivery request! {}", deliveryDto);
        return Mono.just("success").map(s -> ResponseEntity.status(HttpStatus.OK).body(s));
    }

}
