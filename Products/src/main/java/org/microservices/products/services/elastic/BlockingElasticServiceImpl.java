package org.microservices.products.services.elastic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.microservices.products.model.Product;
import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.model.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BlockingElasticServiceImpl implements BlockingElasticService {

    private final RestHighLevelClient highLevelClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    public BlockingElasticServiceImpl(RestHighLevelClient highLevelClient) {
        this.highLevelClient = highLevelClient;
    }

    @Override
    public Optional<List<ProductDto>> getAll() {

        SearchRequest searchRequest = new SearchRequest("product");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(50);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("failed to search elasticsearch ", e);
        }
        if (searchResponse == null || searchResponse.getHits() == null) {
            return Optional.empty();
        }
        List<ProductDto> result = new ArrayList<>();
        Arrays.stream(searchResponse.getHits().getHits()).forEach(hit -> {
            try {
                result.add(mapper.productToDto(objectMapper.readValue(hit.getSourceAsString(), Product.class)));
            } catch (JsonProcessingException e) {
                log.error("failed to parse elastic hit to object ", e);
            }
        });
        return Optional.of(result);
    }
}
