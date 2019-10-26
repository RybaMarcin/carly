package org.carly.parts_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipmentRepository extends MongoRepository<Equipment, ObjectId> {
}
