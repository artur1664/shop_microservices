package org.microservices.products.controllers.routes;

import org.microservices.products.model.Product;
import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.services.ProductSearchService;
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

    //TODO exception handling with router functions

    private final ProductService productService;

    private final ProductSearchService searchService;

    public ProductRoutes(ProductService productService, ProductSearchService searchService) {
        this.productService = productService;
        this.searchService = searchService;
    }

    @Bean
    RouterFunction<ServerResponse> crudOperations() {
        String requestMapping = "/api/v1/route/products/";
        return RouterFunctions.route(GET(requestMapping), req -> ok().body(productService.getAll(), ProductDto.class))
                .andRoute(GET(requestMapping + "{id}"), req -> ok().body(productService.getById(req.pathVariable("id")), ProductDto.class))
                .andRoute(POST(requestMapping), req -> ok().body(req.bodyToMono(ProductDto.class).flatMap(productService::addNewProduct), Void.class))
                .andRoute(PUT(requestMapping), req -> ok().body(req.bodyToMono(ProductDto.class).flatMap(productService::updateProduct), Void.class))
                .andRoute(DELETE(requestMapping + "{id}"), req -> ok().body(productService.deleteProduct(req.pathVariable("id")), Void.class));
    }

    @Bean
    RouterFunction<ServerResponse> searchOperations() {
        String requestMapping = "/api/v1/route/products/springdata/search/";
        return RouterFunctions
                .route(GET(requestMapping), req -> ok().body(searchService.getAllProductsByCategory(req.queryParam("category").orElse("no results")), ProductDto.class))
                .andRoute(GET(requestMapping + "manufacturer"), req -> ok().body(searchService.getAllProductsByManufacturerName(req.queryParam("name").orElse("no results")), ProductDto.class))
                .andRoute(GET(requestMapping + "priceFrom"), req -> ok().body(searchService.getByPriceGreaterThen(req.queryParam("from").orElse("no results")), ProductDto.class))
                .andRoute(GET(requestMapping + "price"), req -> ok().body(searchService.getByPriceRange(req.queryParam("from").orElse(""), req.queryParam("to").orElse("no results")), Product.class));
    }

    @Bean
    RouterFunction<ServerResponse> customSearchOperations() {
        String requestMapping = "/api/v1/route/products/custom/search/";
        return RouterFunctions
                .route(GET(requestMapping), req -> ok().body(searchService.getByPriceRange(Float.parseFloat(req.queryParam("from").orElse("0.0")), Float.parseFloat(req.queryParam("to").orElse("0.0"))), ProductDto.class))
                .andRoute(GET(requestMapping + "page"), req -> ok().body(searchService.findByIndexAndPricePageable(
                        Integer.parseInt(req.queryParam("idxFrom").orElse("0.0")),
                        Integer.parseInt(req.queryParam("idxTo").orElse("0.0")),
                        Float.parseFloat(req.queryParam("from").orElse("0.0")),
                        Float.parseFloat(req.queryParam("to").orElse("0.0")),
                        Integer.parseInt(req.queryParam("pageSize").orElse("0")),
                        Integer.parseInt(req.queryParam("page").orElse("0"))
                ), ProductDto.class))
                .andRoute(GET(requestMapping + "products"), req -> ok().body(searchService.returnProductsOnly(), ProductDto.class))
                .andRoute(GET(requestMapping + "manufacturers"), req -> ok().body(searchService.returnManufacturersOnly(), ProductDto.class));
    }
}
