package org.microservices.products.services.classes;

import org.microservices.products.model.Product;
import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.model.mappers.ProductMapper;
import org.microservices.products.repository.ProductRepository;
import org.microservices.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<ProductDto> addNewProduct(ProductDto productDto) {
        Mono<Product> result = productRepository.save(mapper.dtoToProduct(productDto));
        return result.map(mapper::productToDto);
    }

    @Override
    public Flux<ProductDto> getAll(){
        Flux<Product> result = productRepository.findAll();
        return result.map(mapper::productToDto);
    }
}
