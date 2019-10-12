package org.carly.ordering_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.ordering_management.core.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {

}
