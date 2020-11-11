package org.carly.core.notificationmanagement.repository;

import org.bson.types.ObjectId;
import org.carly.core.notificationmanagement.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, ObjectId> {
}
