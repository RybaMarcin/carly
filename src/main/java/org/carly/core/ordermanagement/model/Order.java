package org.carly.core.ordermanagement.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.order.PartRequest;
import org.carly.core.ordermanagement.model.cart.PartType;
import org.carly.core.vehiclemanagement.model.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "orders")
public class Order {

    public static final String ORDER_ID = "_id";
    public static final String ORDER_CREATE_AT = "createAt";
    public static final String ORDER_MODIFIED_AT = "modifiedAt";
    public static final String ORDER_STATUS = "status";
    public static final String ORDER_CONSUMER_ID = "consumerId";
    public static final String ORDER_CONSUMER_NAME = "consumerName";
    public static final String ORDER_SUPPLIER_ID = "supplierId";
    public static final String ORDER_SUPPLIER_NAME = "supplierName";
    public static final String ORDER_PARTS = "parts";

    @Id
    @Field(ORDER_ID)
    private ObjectId id;
    @Field(ORDER_CREATE_AT)
    private LocalDateTime createAt;
    @Field(ORDER_MODIFIED_AT)
    private LocalDateTime modifiedAt;
    @Field(ORDER_STATUS)
    private Status status;
    @Field(ORDER_CONSUMER_ID)
    private ObjectId consumerId;
    @Field(ORDER_CONSUMER_NAME)
    private String consumerName;
    @Field(ORDER_SUPPLIER_ID)
    private ObjectId supplierId;
    @Field(ORDER_SUPPLIER_NAME)
    private String supplierName;
    @Field(ORDER_PARTS)
    private Map<PartType, List<PartRequest>> partsOrder;
}