package org.microservices.products.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.microservices.products.repository.mongo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("api/v1/products/bootstrap")
public class BootstrapProducts {

    private final ProductRepository productRepository;

    @Autowired
    public BootstrapProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        System.out.println(RandomUtils.nextInt(10000) / 100);
    }

    @PostMapping
    public Mono<Void> loadSomeTestData() {
        //create 100 documents
        return productRepository.deleteAll().thenMany(productRepository.saveAll(BootstrapUtils.prepareSomeProducts())).then();
    }
}
