package org.carly.parts_management.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "equipment")
public class Equipment extends Part {

    private EquipmentType type;
}
