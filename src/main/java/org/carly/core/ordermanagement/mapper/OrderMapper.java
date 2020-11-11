package org.carly.core.ordermanagement.mapper;

import org.bson.types.ObjectId;
import org.carly.api.rest.response.OrderResponse;
import org.carly.core.ordermanagement.model.Order;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.core.vehiclemanagement.model.Status;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements MapperService<OrderResponse, Order> {

    private final TimeService timeService;

    public OrderMapper(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    public OrderResponse mapFromDomainObject(Order domain, OrderResponse rest) {
        rest.setId(domain.getId());
        rest.setCreateAt(domain.getCreateAt());
        rest.setStatus(domain.getStatus().getType());
        rest.setUserId(domain.getUserId());
        rest.setCarId(domain.getCarId());
        return rest;
    }

    @Override
    public Order mapToDomainObject(Order domain, OrderResponse rest) {
        domain.setId(rest.getId() == null ? new ObjectId() : rest.getId());
        domain.setCreateAt(domain.getCreateAt() != null ? timeService.getLocalDate() : rest.getCreateAt());
        domain.setStatus(Status.of(rest.getStatus()));
        domain.setUserId(rest.getUserId());
        domain.setCarId(rest.getCarId());
        return domain;
    }

    @Override
    public OrderResponse simplifyRestObject(Order domain) {
        OrderResponse orderResponse = new OrderResponse();
        return mapFromDomainObject(domain, orderResponse);
    }

    @Override
    public Order simplifyDomainObject(OrderResponse rest) {
        Order order = new Order();
        return mapToDomainObject(order, rest);
    }
}
