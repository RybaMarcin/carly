package org.carly.company_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.company_management.core.model.CompanyChangeRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyChangeRequestRepository extends MongoRepository<CompanyChangeRequest, ObjectId> {
}
