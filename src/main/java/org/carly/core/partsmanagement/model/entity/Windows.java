package org.carly.core.partsmanagement.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Document(collection = "windows")
public class Windows extends Part {
    private String color;
}
