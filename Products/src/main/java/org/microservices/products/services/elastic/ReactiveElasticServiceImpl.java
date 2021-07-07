package org.microservices.products.services.elastic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.microservices.products.model.Product;
import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.model.mappers.ProductMapper;
import org.microservices.products.repository.elastic.ProductElasticReactiveRepository;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class ReactiveElasticServiceImpl implements ReactiveElasticService {

    private final ReactiveElasticsearchClient reactiveElasticsearchClient;

    private final ProductElasticReactiveRepository productElasticReactiveRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    public ReactiveElasticServiceImpl(ReactiveElasticsearchClient reactiveElasticsearchClient,
                                      ProductElasticReactiveRepository productElasticReactiveRepository) {
        this.reactiveElasticsearchClient = reactiveElasticsearchClient;
        this.productElasticReactiveRepository = productElasticReactiveRepository;
    }

    @Override
    public Flux<ProductDto> getAll() {

        SearchRequest searchRequest = new SearchRequest("product");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(50);
        searchRequest.source(searchSourceBuilder);

        Flux<SearchHit> hits = reactiveElasticsearchClient.search(searchRequest);

        return hits
                .map(documentFields -> {
                    try {
                        return objectMapper.readValue(documentFields.getSourceAsString(), Product.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return Flux.empty();
                })
                .filter(o -> o instanceof Product)
                .map(o -> (Product) o)
                .map(mapper::productToDto);
    }

    @Override
    public Flux<ProductDto> getAllFromRepository() {
        return productElasticReactiveRepository.findAll().map(mapper::productToDto);
    }
}
