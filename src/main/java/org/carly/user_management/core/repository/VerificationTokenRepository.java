package org.carly.user_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.user_management.security.token.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends MongoRepository<VerificationToken, ObjectId> {
}
