package org.carly.core.partsmanagement.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "body")
public class Body extends Part{

}
