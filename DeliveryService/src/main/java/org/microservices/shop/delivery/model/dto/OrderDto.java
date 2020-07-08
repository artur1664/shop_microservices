package org.microservices.shop.delivery.model.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private UUID orderUuid;
    private List<ProductDto> products;
    private UUID userUuid;
    private AddressDto address;

}
