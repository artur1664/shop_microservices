package org.microservices.shop.shopping.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.microservices.shop.shopping.model.Order;
import org.microservices.shop.shopping.model.dto.OrderDto;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order dtoToOrder(OrderDto orderDto);

    OrderDto orderToDto(Order order);
}
