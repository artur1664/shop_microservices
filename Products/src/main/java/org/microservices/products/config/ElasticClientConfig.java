package org.microservices.products.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
//@EnableReactiveElasticsearchRepositories(basePackages = {"org.microservices.products.repository.elastic"})
public class ElasticClientConfig {

    private final String elasticHost;
    private final int elasticPort;
    private final String elasticScheme;

    public ElasticClientConfig(@Value("${elastic.host}") String elasticHost,
                               @Value("${elastic.port}") int elasticPort,
                               @Value("${elastic.scheme}") String elasticScheme) {
        this.elasticHost = elasticHost;
        this.elasticPort = elasticPort;
        this.elasticScheme = elasticScheme;
    }

    @Primary
    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(elasticHost, elasticPort, elasticScheme)));

    }
}
