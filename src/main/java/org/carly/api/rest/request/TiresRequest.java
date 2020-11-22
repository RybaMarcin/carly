package org.carly.api.rest.request;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Getter
@Setter
public class TiresRequest {

    private ObjectId id;
    private String name;
    private BigDecimal price;
    private String preview;
}
