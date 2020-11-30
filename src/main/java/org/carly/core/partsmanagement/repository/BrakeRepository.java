package org.carly.core.partsmanagement.repository;


import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.entity.Brake;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface BrakeRepository extends MongoRepository<Brake, ObjectId> {

    Collection<Brake> findAllByFactoryCarlyFactoryId(ObjectId factoryId);
}
