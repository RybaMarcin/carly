package org.carly.parts_management.core.repository;


import org.bson.types.ObjectId;
import org.carly.parts_management.core.model.Breaks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BreaksRepository  extends MongoRepository<Breaks, ObjectId> {
}
