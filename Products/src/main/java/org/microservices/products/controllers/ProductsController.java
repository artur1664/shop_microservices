package org.microservices.products.controllers;

import lombok.extern.slf4j.Slf4j;
import org.microservices.products.model.Product;
import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

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
    public Mono<ProductDto> addNewProduct(@RequestBody ProductDto productDto){
        return productService.addNewProduct(productDto);//todo response ?
    }

    @GetMapping
    public Flux<ProductDto> getAll(){
        log.info("getting all data");
        return productService.getAll();
    }
}
