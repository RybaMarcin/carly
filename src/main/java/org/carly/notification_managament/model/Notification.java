package org.carly.notification_managament.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "notification")
public class Notification {

    public static final String NOTIFICATION_ID = "_id";
    public static final String NOTIFICATION_DESCRIPTION = "description";
    public static final String NOTIFICATION_STATUS = "status";
    public static final String NOTIFICATION_TYPE = "type";
    public static final String NOTIFICATION_RECEIVER = "receiver";
    public static final String NOTIFICATION_TITLE = "title";
    public static final String NOTIFICATION_ACTION_TYPE = "actionType";

    public static final String CREATED_DATE = "createdDate";
    public static final String CREATED_BY = "createdBy";
    public static final String LAST_MODIFIED_DATE = "lastModifiedDate";
    public static final String LAST_MODIFIED_BY = "lastModifiedBy";


    @Field(NOTIFICATION_ID)
    private ObjectId id;
    @Field(NOTIFICATION_DESCRIPTION)
    private String description;
    @Field(NOTIFICATION_STATUS)
    private NotificationStatus status;
    @Field(NOTIFICATION_TYPE)
    private NotificationType type;
    @Field(NOTIFICATION_RECEIVER)
    private NotificationReceiver receiver;
    @Field(NOTIFICATION_TITLE)
    private String notificationTitle;
    @Field(NOTIFICATION_ACTION_TYPE)
    private NotificationActionType actionType;
    @Field(CREATED_DATE)
    private LocalDateTime createDate;

}
