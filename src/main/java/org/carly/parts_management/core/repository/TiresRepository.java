package org.carly.parts_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.Tires;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TiresRepository extends MongoRepository<Tires, ObjectId> {
}
