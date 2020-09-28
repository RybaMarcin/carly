package org.carly.parts_management.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "paintings")
public class Painting extends Part {
    private PaintType type;
}
