package org.microservices.products.services.classes;

import org.microservices.products.model.dto.ProductDto;
import org.microservices.products.model.mappers.ProductMapper;
import org.microservices.products.repository.ProductRepository;
import org.microservices.products.services.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * This class will search with use of method build in spring data
 */
@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductRepository productRepository;

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Autowired
    public ProductSearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<ProductDto> getAllProductsByCategory(String category) {
        return productRepository.findByProductCategory(category).map(mapper::productToDto);
    }

    @Override
    public Flux<ProductDto> getAllProductsByManufacturerName(String name) {
        return productRepository.findByProductDetailsManufacturerName(name).map(mapper::productToDto);
    }

    //this fails
    @Override
    public Flux<ProductDto> getByPriceRange(String from, String to) {
        Float p1 = Float.valueOf(from);
        Float p2 = Float.valueOf(to);
        return productRepository.findByPriceBetween(p1, p2).map(mapper::productToDto);
    }

    @Override
    public Flux<ProductDto> getByPriceGreaterThen(String from) {
        Float p1 = Float.valueOf(from);
        return productRepository.findByPriceGreaterThan(p1).map(mapper::productToDto);
    }

    @Override
    public Flux<ProductDto> getByPriceRange(Float from, Float to) {
        return productRepository.findByPriceRange(from, to).map(mapper::productToDto);
    }

    @Override
    public Flux<ProductDto> findByIndexAndPricePageable(int indexFrom, int indexTo, Float priceFrom, Float priceTo, int pageSize, int page) {
        return productRepository.findByIndexAndPricePageable(indexFrom, indexTo, priceFrom, priceTo, pageSize, page).map(mapper::productToDto);
    }

    @Override
    public Flux<ProductDto> returnProductsOnly() {
        return productRepository.returnProductsOnly().map(mapper::productToDto);
    }

    @Override
    public Flux<ProductDto> returnManufacturersOnly() {
        return productRepository.returnManufacturersOnly().map(mapper::productToDto);
    }
}
