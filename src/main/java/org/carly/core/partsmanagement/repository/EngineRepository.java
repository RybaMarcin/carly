package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.Engine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EngineRepository extends MongoRepository<Engine, ObjectId> {




}
