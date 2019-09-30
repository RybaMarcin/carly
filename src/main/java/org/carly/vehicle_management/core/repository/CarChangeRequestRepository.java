package org.carly.vehicle_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.CarChangeRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarChangeRequestRepository extends MongoRepository<CarChangeRequest, ObjectId> {
}
