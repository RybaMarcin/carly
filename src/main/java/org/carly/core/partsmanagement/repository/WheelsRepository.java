package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.entity.Wheels;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface WheelsRepository extends MongoRepository<Wheels, ObjectId> {

    Collection<Wheels> findAllByFactoryCarlyFactoryId(ObjectId factoryId);

}
