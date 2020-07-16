package org.microservices.products.repository;

import org.microservices.products.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String>, ProductCustomRepository {

    Flux<Product> findByProductCategory(String category);

    Flux<Product> findByProductDetailsManufacturerName(String name);

    Flux<Product> findByPriceBetween(Float from, Float to);

    Flux<Product> findByPriceGreaterThan(Float from);

}
