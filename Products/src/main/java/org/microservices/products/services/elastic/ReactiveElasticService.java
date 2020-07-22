package org.microservices.products.services.elastic;

import org.microservices.products.model.dto.ProductDto;
import reactor.core.publisher.Flux;

public interface ReactiveElasticService {

    Flux<ProductDto> getAll();
}
