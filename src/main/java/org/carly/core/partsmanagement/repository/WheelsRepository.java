package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.entity.Wheels;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WheelsRepository extends MongoRepository<Wheels, ObjectId> {



}
