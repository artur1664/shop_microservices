package org.microservices.products.services.elastic;

import org.microservices.products.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface BlockingElasticService {

    Optional<List<ProductDto>> getAll();
}
