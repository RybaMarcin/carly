package org.carly.core.vehiclemanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.vehiclemanagement.model.CarChangeRequest;
import org.carly.core.vehiclemanagement.model.ChangeRequestStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarChangeRequestRepository extends MongoRepository<CarChangeRequest, ObjectId> {

    Optional<CarChangeRequest> findByIdAndStatus(ObjectId carId, ChangeRequestStatus status);
}
