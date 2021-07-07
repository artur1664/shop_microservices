package org.microservices.products.services;

import org.microservices.products.model.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Void> addNewProduct(ProductDto productDto);

    Flux<ProductDto> getAll();

    Mono<ProductDto> getById(String id);

    Mono<Void> updateProduct(ProductDto productDto);

    Mono<Void> deleteProduct(String id);
}
