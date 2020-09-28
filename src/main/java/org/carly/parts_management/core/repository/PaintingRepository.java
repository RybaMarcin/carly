package org.carly.parts_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.Painting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaintingRepository extends MongoRepository<Painting, ObjectId> {
}
