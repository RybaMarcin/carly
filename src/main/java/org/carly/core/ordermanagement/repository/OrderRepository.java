package org.carly.core.ordermanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.ordermanagement.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {

}
