package org.carly.core.shared.utils.mail_service;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailDataRepository extends MongoRepository<MailData, ObjectId> {
}
