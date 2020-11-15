package org.carly.api.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.carly.core.partsmanagement.model.dictionaries.BreaksType;

import java.math.BigDecimal;

@Getter
@Setter
public class BreaksResponse {
    private String id;

    private String name;
    private BrandResponse brand;
    private String preview;
    private BreaksType breaksType;
    private BigDecimal price;
}
