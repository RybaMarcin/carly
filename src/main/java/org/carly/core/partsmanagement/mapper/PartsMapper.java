package org.carly.core.partsmanagement.mapper;

import org.carly.api.rest.response.factories.PartMinModel;
import org.carly.core.partsmanagement.model.entity.Part;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PartsMapper {

    public List<PartMinModel> mapFromPartToMinModel(Collection<? extends Part> supplierParts) {
        List<PartMinModel> minModels = new ArrayList<>();
        for (Part supplierPart : supplierParts) {
            PartMinModel partMinModel =
                    new PartMinModel(supplierPart.getId().toHexString(),
                            supplierPart.getName(),
                            supplierPart.getPrice(),
                            supplierPart.getPartType()
                    );
            minModels.add(partMinModel);
        }
        return minModels;
    }
}
