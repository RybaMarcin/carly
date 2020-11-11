package org.carly.core.partsmanagement.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tires")
public class Tires extends Part {

}
