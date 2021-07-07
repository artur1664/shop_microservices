package org.microservices.shop.shopping.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto {

    private String street;

    private String streetNumber;

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
}
