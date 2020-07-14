package org.microservices.products.repository;

import org.microservices.products.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByProductCategory(String category);

    Flux<Product> findByProductDetailsManufacturerName(String name);

    //spring limitation ? query fails ! Due to limitations of the com.mongodb.BasicDocument, you can't add a second 'price' expression specified as 'price
    Flux<Product> findByPriceGreaterThanAndPriceLessThan(Float from, Float to);

    Flux<Product> findByPriceGreaterThan(Float from);

}
