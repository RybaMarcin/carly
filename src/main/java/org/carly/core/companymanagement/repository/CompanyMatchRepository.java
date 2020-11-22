package org.carly.core.companymanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.companymanagement.model.CompanyMatch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyMatchRepository extends MongoRepository<CompanyMatch, ObjectId> {
}
