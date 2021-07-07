package org.microservices.products.controllers;

import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.services.elastic.ReactiveElasticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/reactive/elastic")
public class ElasticReactiveController {

    private final ReactiveElasticService reactiveElasticService;

    public ElasticReactiveController(ReactiveElasticService reactiveElasticService) {
        this.reactiveElasticService = reactiveElasticService;
    }

    @GetMapping
    public Flux<ProductDto> getAll() {
        return reactiveElasticService.getAll();
    }

    @GetMapping("/repo")
    public Flux<ProductDto> getAllWithSpringDataRepository() {
        return reactiveElasticService.getAllFromRepository();
    }
}
