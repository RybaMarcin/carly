package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.Tires;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TiresRepository extends MongoRepository<Tires, ObjectId> {
}
