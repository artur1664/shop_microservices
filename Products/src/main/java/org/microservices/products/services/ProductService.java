package org.microservices.products.services;

import org.microservices.products.model.Product;
import org.microservices.products.model.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductDto> addNewProduct(ProductDto productDto);

    Flux<ProductDto> getAll();
}
