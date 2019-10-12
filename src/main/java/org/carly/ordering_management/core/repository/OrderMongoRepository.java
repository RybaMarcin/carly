package org.carly.ordering_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.ordering_management.core.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.carly.ordering_management.core.model.Order.ORDER_CUSTOMER;
import static org.carly.shared.utils.criteria.CriteriaBuilder.criteria;

@Repository
public class OrderMongoRepository {

    private final MongoTemplate mongoTemplate;

    public OrderMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Order> findAllOrdersByCustomerId(ObjectId customerId, Pageable pageable) {
        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                criteria(ORDER_CUSTOMER, Criteria::is, customerId));

        Query query = new Query(criteria);
        long count = mongoTemplate.count(query, Order.class);
        List<Order> orders = mongoTemplate.find(query.with(pageable), Order.class);
        return new PageImpl<>(orders, pageable, count);
    }
}
