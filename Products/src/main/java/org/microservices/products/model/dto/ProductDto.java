package org.microservices.products.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.microservices.products.model.ProductCategory;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private String name;
    private UUID productUuid;
    private Float price;
    private Integer index;
    private String description;
    private ProductCategory productCategory;
    private ProductDetailsDto productDetails;
}