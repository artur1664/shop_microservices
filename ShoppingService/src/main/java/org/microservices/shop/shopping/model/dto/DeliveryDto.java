package org.microservices.shop.shopping.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    private OrderDto orderDto;
    private String additionalInfo;
}
