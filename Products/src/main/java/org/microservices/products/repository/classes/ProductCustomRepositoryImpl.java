package org.microservices.products.repository.classes;

import lombok.extern.slf4j.Slf4j;
import org.microservices.products.model.Product;
import org.microservices.products.repository.ProductCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Slf4j
@Repository
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    //criteria examples

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public ProductCustomRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Flux<Product> findByPriceRange(Float from, Float to) {
        Criteria criteria = Criteria.where("price").gt(from).andOperator(Criteria.where("price").lt(to));
        Query query = new Query(criteria);

        return reactiveMongoTemplate.find(query, Product.class);
    }

    @Override
    public Flux<Product> findByIndexAndPricePageable(int indexFrom, int indexTo, Float priceFrom, Float priceTo, int pageSize, int page) {
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("index").lt(indexTo),
                Criteria.where("index").gt(indexFrom),
                Criteria.where("price").lt(priceTo),
                Criteria.where("price").gt(priceFrom)
        );

        Query query = new Query(criteria);

        //noinspection CallingSubscribeInNonBlockingScope
        this.reactiveMongoTemplate.count(query, Product.class).subscribe(aLong -> log.info("total results: {}", aLong));

        query.with(PageRequest.of(page, pageSize, Sort.Direction.ASC, "index"));
        return reactiveMongoTemplate.find(query, Product.class);
    }

    @Override
    public Flux<Product> returnProductsOnly() {
        Query query = new Query();
        query.fields().include("productUuid");
        query.fields().include("name");
        query.fields().include("price");
        query.fields().include("index");
        query.fields().include("description");
        query.fields().include("productCategory");

        return reactiveMongoTemplate.find(query, Product.class);
    }

    @Override
    public Flux<Product> returnManufacturersOnly() {
        Query query = new Query();
        query.fields().include("productDetails.manufacturer");

        return reactiveMongoTemplate.find(query, Product.class);
    }


}
