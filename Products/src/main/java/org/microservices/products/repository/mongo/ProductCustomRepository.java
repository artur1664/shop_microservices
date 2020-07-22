package org.microservices.products.repository.mongo;

import org.microservices.products.model.Product;
import reactor.core.publisher.Flux;

public interface ProductCustomRepository {

    Flux<Product> findByPriceRange(Float from, Float to);

    Flux<Product> findByIndexAndPricePageable(int indexFrom, int indexTo, Float priceFrom, Float priceTo, int pageSize, int page);

    Flux<Product> returnProductsOnly();

    Flux<Product> returnManufacturersOnly();
}
