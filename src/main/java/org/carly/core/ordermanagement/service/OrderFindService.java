package org.carly.core.ordermanagement.service;

import org.bson.types.ObjectId;
import org.carly.api.rest.response.OrderResponse;
import org.carly.core.ordermanagement.mapper.OrderMapper;
import org.carly.core.ordermanagement.repository.OrderMongoRepository;
import org.carly.core.ordermanagement.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderFindService {

    private final OrderRepository orderRepository;
    private final OrderMongoRepository orderMongoRepository;
    private final OrderMapper orderMapper;

    public OrderFindService(OrderRepository orderRepository,
                            OrderMongoRepository orderMongoRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMongoRepository = orderMongoRepository;
        this.orderMapper = orderMapper;
    }

    public OrderResponse findOrderById(ObjectId orderId) {
//        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
//        return orderMapper.simplifyRestObject(order);
        return null;
    }

    public Page<OrderResponse> findByCustomerId(ObjectId customerId, Pageable pageable) {
//        Page<Order> allCustomerOrders = orderMongoRepository.findAllOrdersByCustomerId(customerId, pageable);
//        return allCustomerOrders.map(orderMapper::simplifyRestObject);
        return null;
    }
}