package org.carly.vehicle_management.core.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "MODELS")
public class Model {

    private ObjectId id;
    private String name;
}
