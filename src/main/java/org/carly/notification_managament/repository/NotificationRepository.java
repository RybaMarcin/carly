package org.carly.notification_managament.repository;

import org.bson.types.ObjectId;
import org.carly.notification_managament.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, ObjectId> {
}
