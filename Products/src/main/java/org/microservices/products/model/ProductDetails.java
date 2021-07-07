package org.microservices.products.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetails {

    private String moreInfo;
    private UUID uuid;
    private Manufacturer manufacturer;
}
