package org.carly.core.partsmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.Painting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaintingRepository extends MongoRepository<Painting, ObjectId> {
}
