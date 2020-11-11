package org.carly.core.usermanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.usermanagement.model.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends MongoRepository<VerificationToken, ObjectId> {

    Optional<VerificationToken> findByToken(String token);
}
