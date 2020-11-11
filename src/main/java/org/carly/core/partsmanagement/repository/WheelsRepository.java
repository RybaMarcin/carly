package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.Wheels;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WheelsRepository extends MongoRepository<Wheels, ObjectId> {



}
