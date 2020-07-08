package org.microservices.shop.shopping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient createWebClient(@Value("${delivery.base.url}") String baseUrl) {
        return WebClient.create(baseUrl);
    }
}
