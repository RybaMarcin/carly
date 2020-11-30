package org.carly.core.companymanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.companymanagement.model.CompanyMatch;
import org.carly.core.companymanagement.model.CompanyMatchStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyMatchRepository extends MongoRepository<CompanyMatch, ObjectId> {

    List<CompanyMatch> findAllByCompanyIdAndStatus(ObjectId companyId, CompanyMatchStatus status);
}
