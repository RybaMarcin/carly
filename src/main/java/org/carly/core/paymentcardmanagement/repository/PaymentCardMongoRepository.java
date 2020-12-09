package org.carly.core.paymentcardmanagement.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentCardMongoRepository {

    private final MongoTemplate mongoTemplate;

    public PaymentCardMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
