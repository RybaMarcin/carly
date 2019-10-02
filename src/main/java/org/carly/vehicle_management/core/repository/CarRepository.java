package org.carly.vehicle_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.Car;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car, ObjectId> {

    Car findByVinNumberAndRequestStatus(String vinNumber, ChangeRequestStatus status);
}
