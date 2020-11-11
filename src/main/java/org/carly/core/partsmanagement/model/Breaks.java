package org.carly.core.partsmanagement.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "breaks")
public class Breaks extends Part {

    private BreaksType breaksType;

}
