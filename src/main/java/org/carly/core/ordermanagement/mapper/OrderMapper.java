package org.carly.core.ordermanagement.mapper;

import org.bson.types.ObjectId;
import org.carly.api.rest.request.order.OrderRequest;
import org.carly.core.ordermanagement.model.Order;
import org.carly.core.shared.utils.MapperService;
import org.carly.core.shared.utils.time.TimeService;
import org.carly.core.vehiclemanagement.model.Status;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements MapperService<OrderRequest, Order> {

    private final TimeService timeService;

    public OrderMapper(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    public OrderRequest mapFromDomainObject(Order domain, OrderRequest request) {
        if (domain.getId() != null) {
            request.setOrderId(domain.getId().toHexString());
        }
        request.setConsumerId(domain.getConsumerId().toHexString());
        request.setSupplierId(domain.getSupplierId().toHexString());
        request.setConsumerName(domain.getConsumerName());
        request.setSupplierName(domain.getSupplierName());
        request.setCreateAt(domain.getCreateAt());
        if (domain.getModifiedAt() != null) {
            request.setModifiedAt(domain.getModifiedAt());
        }
        request.setStatus(domain.getStatus());
        return request;
    }

    @Override
    public Order mapToDomainObject(Order domain, OrderRequest rest) {
        if (rest.getOrderId() != null) {
            domain.setId(new ObjectId(rest.getOrderId()));
        }
        domain.setConsumerId(new ObjectId(rest.getConsumerId()));
        domain.setConsumerName(rest.getConsumerName());
        domain.setCreateAt(rest.getCreateAt() == null ? timeService.getLocalDateTime() : rest.getCreateAt());
        domain.setSupplierId(new ObjectId(rest.getSupplierId()));
        domain.setSupplierName(rest.getSupplierName());
        domain.setStatus(rest.getStatus());
        if (rest.getModifiedAt() !=null) {
            domain.setModifiedAt(rest.getModifiedAt());
        }
        return domain;
    }

    @Override
    public OrderRequest simplifyRestObject(Order domain) {
        OrderRequest orderRequest = new OrderRequest();
        return mapFromDomainObject(domain, orderRequest);
    }

    @Override
    public Order simplifyDomainObject(OrderRequest rest) {
        Order order = new Order();
        return mapToDomainObject(order, rest);
    }
}
