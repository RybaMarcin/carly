package org.carly.core.ordermanagement.service;

import org.bson.types.ObjectId;
import org.carly.api.rest.OrderRest;
import org.carly.core.ordermanagement.mapper.OrderMapper;
import org.carly.core.ordermanagement.model.Order;
import org.carly.core.ordermanagement.repository.OrderMongoRepository;
import org.carly.core.ordermanagement.repository.OrderRepository;
import org.carly.core.shared.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

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

    public OrderRest findOrderById(ObjectId orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        return orderMapper.simplifyRestObject(order);
    }

    public Page<OrderRest> findByCustomerId(ObjectId customerId, Pageable pageable) {
        Page<Order> allCustomerOrders = orderMongoRepository.findAllOrdersByCustomerId(customerId, pageable);
        return allCustomerOrders.map(orderMapper::simplifyRestObject);
    }
}