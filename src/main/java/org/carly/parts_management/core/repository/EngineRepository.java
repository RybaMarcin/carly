package org.carly.parts_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Engine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EngineRepository extends MongoRepository<Engine, ObjectId> {




}
