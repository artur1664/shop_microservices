package org.microservices.products.controllers;

import org.microservices.products.services.elastic.BlockingElasticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/blocking/elastic")
public class ElasticBlockingController {

    private final BlockingElasticService blockingElasticService;

    public ElasticBlockingController(BlockingElasticService blockingElasticService) {
        this.blockingElasticService = blockingElasticService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return blockingElasticService.getAll().map(productDto -> new ResponseEntity<>(productDto, HttpStatus.OK)).orElseThrow(RuntimeException::new);//not found instead RuntimeException
    }
}
