package org.microservices.products.services.classes;

import lombok.extern.slf4j.Slf4j;
import org.microservices.products.model.Product;
import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.model.mappers.ProductMapper;
import org.microservices.products.repository.ProductRepository;
import org.microservices.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Void> addNewProduct(ProductDto productDto) {
        log.info("create new product");
        return productRepository.save(mapper.dtoToProduct(productDto)).then();
    }

    @Override
    public Flux<ProductDto> getAll(){
        log.info("create get all products");
        Flux<Product> result = productRepository.findAll();
        return result.map(mapper::productToDto);
    }

    @Override
    public Mono<ProductDto> getById(String id) {
        log.info("get product by id");
        return productRepository.findById(id).map(mapper::productToDto);
    }

    @Override
    public Mono<Void> deleteProduct(String id){
        log.info("delete product");
        return productRepository.deleteById(id);
    }

    @Override
    public Mono<Void> updateProduct(ProductDto productDto){
        Product product = mapper.dtoToProduct(productDto);

        return productRepository.save(product).then();

    }
}
