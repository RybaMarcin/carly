package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.Windows;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WindowsRepository extends MongoRepository<Windows, ObjectId> {
}
