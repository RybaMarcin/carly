package org.carly.user_management.api.model;

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
