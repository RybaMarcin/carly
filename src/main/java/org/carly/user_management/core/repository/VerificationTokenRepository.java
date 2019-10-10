package org.carly.user_management.core.repository;

import org.bson.types.ObjectId;
import org.carly.user_management.core.model.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends MongoRepository<VerificationToken, ObjectId> {

    Optional<VerificationToken> findByToken(String token);
}
