package org.carly.core.vehiclemanagement.model;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String number;
    private String flat;
    private String town;
    private String zipCode;
    private String country;
}
