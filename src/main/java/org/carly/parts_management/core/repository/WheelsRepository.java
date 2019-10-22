package org.carly.parts_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.Wheels;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WheelsRepository extends MongoRepository<Wheels, ObjectId> {



}
