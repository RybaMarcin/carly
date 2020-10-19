package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipmentRepository extends MongoRepository<Equipment, ObjectId> {
}
