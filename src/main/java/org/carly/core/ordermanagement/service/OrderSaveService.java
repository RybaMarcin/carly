package org.carly.core.ordermanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.OrderRest;
import org.carly.core.ordermanagement.mapper.OrderMapper;
import org.carly.core.ordermanagement.model.Order;
import org.carly.core.ordermanagement.repository.OrderRepository;
import org.carly.core.shared.config.EntityNotFoundException;
import org.carly.core.shared.utils.time.TimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.carly.core.shared.utils.InfoUtils.NOT_FOUND;

@Slf4j
@Service
public class OrderSaveService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final TimeService timeService;

    public OrderSaveService(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            TimeService timeService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.timeService = timeService;
    }

    public ObjectId save(OrderRest orderRest) {
        Order order = orderMapper.simplifyDomainObject(orderRest);
        return orderRepository.save(order).getId();
    }

    public ResponseEntity updateOrder(OrderRest orderRest) {
        Order oldOrder = orderRepository.findById(orderRest.getId()).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        Order newOrder = orderMapper.mapToDomainObject(oldOrder, orderRest);
        newOrder.setCreateAt(oldOrder.getCreateAt());
        newOrder.setModifiedAt(timeService.getLocalDate());
        orderRepository.save(newOrder);
        log.info("Order update successfully");
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteOrder(ObjectId orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        orderRepository.delete(order);
        log.info("Order with id: {} - was successful deleted", orderId);
        return ResponseEntity.ok().build();
    }
}