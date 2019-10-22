package org.carly.parts_management.core.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WheelsMongoRepository {


    private final MongoTemplate mongoTemplate;


    public WheelsMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
