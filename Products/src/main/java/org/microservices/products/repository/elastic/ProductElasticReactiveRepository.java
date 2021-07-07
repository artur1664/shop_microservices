package org.microservices.products.repository.elastic;

import org.microservices.products.model.Product;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductElasticReactiveRepository extends ReactiveElasticsearchRepository<Product, String> {
}
