package org.carly.core.usermanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Address {
    private String street;
    private String number;
    private String flat;
    private String town;
    private String zipCode;
    private String country;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
}
