package org.carly.vehicle_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.vehicle_management.core.model.CarChangeRequest;
import org.carly.vehicle_management.core.model.ChangeRequestStatus;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface CarChangeRequestRepository extends MongoRepository<CarChangeRequest, ObjectId> {

    Optional<CarChangeRequest> findByIdAndStatus(ObjectId carId, ChangeRequestStatus status);
}
