package org.carly.core.companymanagement.mapper;

import org.carly.api.rest.response.FactoryDetailsResponse;
import org.carly.api.rest.response.factories.PartMinModel;
import org.carly.core.partsmanagement.model.entity.Part;
import org.carly.core.usermanagement.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class FactoryDetailMapper {

    public FactoryDetailsResponse mapFromDomain(User user) {
        FactoryDetailsResponse response = new FactoryDetailsResponse();
        response.setFactoryName(user.getCompany().getName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setEmail(user.getEmail());
        if (user.getAddress() != null) {
            response.setStreet(user.getAddress().getStreet());
            response.setNumber(user.getAddress().getNumber());
            response.setFlat(user.getAddress().getFlat());
            response.setZipCode(user.getAddress().getZipCode());
            response.setCountry(user.getAddress().getCountry());
        }
        return response;
    }

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
