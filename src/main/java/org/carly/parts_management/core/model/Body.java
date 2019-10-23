package org.carly.parts_management.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "body")
public class Body extends Part{



}
