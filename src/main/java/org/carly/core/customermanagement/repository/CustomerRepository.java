package org.carly.core.customermanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.customermanagement.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {
}
