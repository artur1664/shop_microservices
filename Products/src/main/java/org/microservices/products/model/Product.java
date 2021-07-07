package org.microservices.products.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "product")
@Builder
public class Product {

    @Id
    private String id;
    private UUID productUuid;
    private String name;
    private Float price;
    private Integer index;
    private String description;
    private ProductCategory productCategory;
    private ProductDetails productDetails;

}
