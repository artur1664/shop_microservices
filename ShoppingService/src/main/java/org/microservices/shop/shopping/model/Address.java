package org.microservices.shop.shopping.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Address {

    private String street;

    private String streetNumber;

    private Order order;

}
