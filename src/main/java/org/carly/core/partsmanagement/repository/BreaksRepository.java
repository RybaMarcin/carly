package org.carly.core.partsmanagement.repository;


import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.Breaks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BreaksRepository  extends MongoRepository<Breaks, ObjectId> {
}
