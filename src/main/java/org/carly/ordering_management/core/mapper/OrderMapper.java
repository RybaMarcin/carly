package org.carly.ordering_management.core.mapper;

import org.bson.types.ObjectId;
import org.carly.ordering_management.api.model.OrderRest;
import org.carly.ordering_management.core.model.Order;
import org.carly.shared.utils.MapperService;
import org.carly.shared.utils.time.TimeService;
import org.carly.vehicle_management.core.model.Status;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements MapperService<OrderRest, Order> {

    private final TimeService timeService;

    public OrderMapper(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    public OrderRest mapFromDomainObject(Order domain, OrderRest rest) {
        rest.setId(domain.getId());
        rest.setCreateAt(domain.getCreateAt());
        rest.setStatus(domain.getStatus().getType());
        rest.setUserId(domain.getUserId());
        rest.setCarId(domain.getCarId());
        return rest;
    }

    @Override
    public Order mapToDomainObject(Order domain, OrderRest rest) {
        domain.setId(rest.getId() == null ? new ObjectId() : rest.getId());
        domain.setCreateAt(domain.getCreateAt() != null ? timeService.getLocalDate() : rest.getCreateAt());
        domain.setStatus(Status.of(rest.getStatus()));
        domain.setUserId(rest.getUserId());
        domain.setCarId(rest.getCarId());
        return domain;
    }

    @Override
    public OrderRest simplifyRestObject(Order domain) {
        OrderRest orderRest = new OrderRest();
        return mapFromDomainObject(domain, orderRest);
    }

    @Override
    public Order simplifyDomainObject(OrderRest rest) {
        Order order = new Order();
        return mapToDomainObject(order, rest);
    }
}
