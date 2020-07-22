package org.microservices.products.bootstrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.microservices.products.model.Product;
import org.microservices.products.repository.mongo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api/v1/products/elastic/bootstrap")
public class BootstrapElastic {

    private final RestHighLevelClient highLevelClient;

    private final ProductRepository productRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public BootstrapElastic(RestHighLevelClient highLevelClient, ProductRepository productRepository) {
        this.highLevelClient = highLevelClient;
        this.productRepository = productRepository;
    }

    @PostMapping
    public String loadSomeTestData() {
        log.info("bootstrapping some test elastic search data");
        BulkRequest bulkRequest = new BulkRequest();
        Flux<Product> fluxData = productRepository.findAll();

        fluxData.map(product -> {
            try {
                bulkRequest.add(new IndexRequest("product").id(product.getId()).source(mapper.writeValueAsString(product), XContentType.JSON));
            } catch (JsonProcessingException e) {
                log.error("parsing error ", e);
            }
            return product;
        }).doFinally(signalType -> {
            try {
                highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                log.error("failed bulk request ", e);
            }
        }).subscribe();

        return "finished";
    }
}
