package org.carly.core.companymanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.companymanagement.model.CompanyChangeRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyChangeRequestRepository extends MongoRepository<CompanyChangeRequest, ObjectId> {
}
