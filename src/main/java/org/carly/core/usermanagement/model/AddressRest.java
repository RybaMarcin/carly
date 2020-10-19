package org.carly.core.usermanagement.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRest {
    private String street;
    private String number;
    private String flat;
    private String town;
    private String zipCode;
    private String country;
}
