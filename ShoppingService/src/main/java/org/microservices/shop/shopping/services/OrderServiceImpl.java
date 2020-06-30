package org.microservices.shop.shopping.services;

import org.microservices.shop.shopping.mappers.OrderMapper;
import org.microservices.shop.shopping.model.Order;
import org.microservices.shop.shopping.model.dto.OrderDto;
import org.microservices.shop.shopping.repostitory.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private OrderMapper mapper = OrderMapper.INSTANCE;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Mono<OrderDto> addNewOrder(OrderDto orderDto) {
        Order newOrder = mapper.dtoToOrder(orderDto);
        newOrder.setOrderUuid(UUID.randomUUID());
        return orderRepository.save(newOrder).map(order -> mapper.orderToDto(order));
    }
}
