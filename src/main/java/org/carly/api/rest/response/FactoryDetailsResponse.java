package org.carly.api.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carly.core.partsmanagement.model.entity.Part;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactoryDetailsResponse {

    private String factoryName;
    private String phoneNumber;
    private String email;
    private String street;
    private String number;
    private String flat;
    private String town;
    private String zipCode;
    private String country;
    private String matchStatus;

    private Collection<? extends Part> parts;
}
