package org.microservices.products.controllers;

import org.microservices.products.model.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

/**
 * Products reactive controller
 */
@RestController
@RequestMapping("api/v1/products")
public class ProductsController {

    //for example method can return 100 products with best ratings
    @GetMapping
    public Flux<ProductDto> getSomeProducts(){

        //todo create and implement service
        //mock response
        ProductDto productDto = new ProductDto(1L, "milk", new BigDecimal("2.44"), "best milk of all time");
        ProductDto productDto2 = new ProductDto(2L, "beer", new BigDecimal("4.21"), "best beer of all time");
        ProductDto productDto3 = new ProductDto(3L, "water", new BigDecimal("5.32"), "best water of all time");

        Flux<ProductDto> result = Flux.just(productDto, productDto2, productDto3);

        return result;
    }
}
