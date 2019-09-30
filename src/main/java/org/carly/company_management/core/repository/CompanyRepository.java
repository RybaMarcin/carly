package org.carly.company_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.company_management.core.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, ObjectId> {
}
