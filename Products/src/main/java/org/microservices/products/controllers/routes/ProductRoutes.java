package org.microservices.products.controllers.routes;

import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class ProductRoutes {

    private final ProductService productService;

    private final String requestMapping = "/api/v1/route/products/";

    public ProductRoutes(ProductService productService) {
        this.productService = productService;
    }

    @Bean
    RouterFunction<ServerResponse> crudOperations() {
        return RouterFunctions.route(GET(requestMapping), serverRequest -> ok().body(productService.getAll(), ProductDto.class))
                .andRoute(GET(requestMapping + "{id}"), serverRequest -> ok().body(productService.getById(serverRequest.pathVariable("id")), ProductDto.class))
                .andRoute(POST(requestMapping), serverRequest -> ok().body(serverRequest.bodyToMono(ProductDto.class).flatMap(productService::addNewProduct), Void.class))
                .andRoute(PUT(requestMapping), serverRequest -> ok().body(serverRequest.bodyToMono(ProductDto.class).flatMap(productService::updateProduct), Void.class))
                .andRoute(DELETE(requestMapping + "{id}"), serverRequest -> ok().body(productService.deleteProduct(serverRequest.pathVariable("id")), Void.class));
    }
}
