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
public class Manufacturer {

    private String name;
    private UUID uuid;
    private String nip;
    private Address address;
}
