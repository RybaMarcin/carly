package org.carly.parts_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.Windows;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WindowsRepository extends MongoRepository<Windows, ObjectId> {
}
