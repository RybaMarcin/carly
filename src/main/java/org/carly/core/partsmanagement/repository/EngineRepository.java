package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.entity.Engine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface EngineRepository extends MongoRepository<Engine, ObjectId> {

    Collection<Engine> findAllByFactoryCarlyFactoryId(ObjectId factoryId);
}
