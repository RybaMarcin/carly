package org.carly.core.ordermanagement.model.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecificFactory {
    private String factoryId;
    private String factoryName;
    private Map<PartType, List<SpecificPart>> parts;

}
