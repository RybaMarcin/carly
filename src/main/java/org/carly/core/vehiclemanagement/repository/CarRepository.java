package org.carly.core.vehiclemanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.vehiclemanagement.model.Car;
import org.carly.core.vehiclemanagement.model.ChangeRequestStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car, ObjectId> {

    Car findByVinNumberAndRequestStatus(String vinNumber, ChangeRequestStatus status);
}
