package org.microservices.products.controllers;

import lombok.extern.slf4j.Slf4j;
import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Products reactive controller
 */
@Slf4j
@RestController
@RequestMapping("api/v1/products")
public class ProductsController {

    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Mono<Void> addNewProduct(@RequestBody ProductDto productDto) {
        log.info("add new product controller");
        return productService.addNewProduct(productDto);//todo response ?
    }

    @GetMapping
    public Flux<ProductDto> getAll() {
        log.info("get all products controller");
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getById(@PathVariable String id) {
        log.info("get product by id controller");
        return productService.getById(id);
    }

    @PutMapping
    public Mono<Void> updateProduct(@RequestBody ProductDto productDto) {
        log.info("update product id controller");
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable String id) {
        log.info("delete product by id controller");
        return productService.deleteProduct(id);
    }

    @GetMapping("/log")
    public Mono<String> justLogMessages() {
        //todo dont commit this controller
        log.info("Just testing logs !!!! {}", UUID.randomUUID());
        return Mono.just("added some logs");
    }
}
