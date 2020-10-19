package org.carly.core.partsmanagement.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "models")
public class Model {
    private ObjectId id;
    private String name;
}
