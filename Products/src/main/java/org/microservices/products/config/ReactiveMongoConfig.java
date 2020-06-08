package org.microservices.products.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = {"org.microservices.products.repository"})
public class ReactiveMongoConfig {

}
