package org.carly.core.partsmanagement.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.carly.core.partsmanagement.model.dictionaries.BreaksType;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Document(collection = "breaks")
public class Breaks extends Part {

    private BreaksType breaksType;

}
