package org.microservices.products.services;

import org.microservices.products.model.dto.ProductDto;
import reactor.core.publisher.Flux;

public interface ProductSearchService {

    Flux<ProductDto> getAllProductsByCategory(String productCategory);

    Flux<ProductDto> getAllProductsByManufacturerName(String name);

    Flux<ProductDto> getByPriceRange(String from, String to);

    Flux<ProductDto> getByPriceGreaterThen(String from);

    Flux<ProductDto> getByPriceRange(Float from, Float to);

    Flux<ProductDto> findByIndexAndPricePageable(int indexFrom, int indexTo, Float priceFrom, Float priceTo, int pageSize, int page);

    Flux<ProductDto> returnProductsOnly();

    Flux<ProductDto> returnManufacturersOnly();

}
