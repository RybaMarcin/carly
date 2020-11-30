package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.entity.Brake;
import org.carly.core.partsmanagement.model.entity.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface EquipmentRepository extends MongoRepository<Equipment, ObjectId> {

    Collection<Equipment> findAllByFactoryCarlyFactoryId(ObjectId factoryId);

}
