package org.carly.core.ordermanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.ordermanagement.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.carly.core.ordermanagement.model.Order.ORDER_CONSUMER_ID;
import static org.carly.core.shared.utils.criteria.CriteriaBuilder.criteria;

@Repository
public class OrderMongoRepository {

    private final MongoTemplate mongoTemplate;

    public OrderMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Order> findAllOrdersByCustomerId(ObjectId customerId, Pageable pageable) {
        Criteria criteria = criteria(new Criteria(), Criteria::andOperator,
                criteria(ORDER_CONSUMER_ID, Criteria::is, customerId));

        Query query = new Query(criteria);
        long count = mongoTemplate.count(query, Order.class);
        List<Order> orders = mongoTemplate.find(query.with(pageable), Order.class);
        return new PageImpl<>(orders, pageable, count);
    }
}
