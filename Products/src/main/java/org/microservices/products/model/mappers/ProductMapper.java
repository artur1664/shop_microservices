package org.microservices.products.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.microservices.products.model.Product;
import org.microservices.products.model.dto.ProductDto;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product dtoToProduct(ProductDto productDto);

    ProductDto productToDto(Product product);
}
