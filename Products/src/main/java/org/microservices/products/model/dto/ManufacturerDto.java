package org.microservices.products.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ManufacturerDto {

    private String name;
    private UUID uuid;
    private String nip;
    private AddressDto address;
}
