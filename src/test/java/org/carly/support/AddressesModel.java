package org.carly.support;

import org.carly.core.usermanagement.model.AddressRest;
import org.carly.core.usermanagement.model.Address;

import static org.carly.core.shared.utils.builder.Builder.anObject;

public class AddressesModel {

    public static final String STREET_1 = "Wolf Street";
    public static final String NUMBER_1 = "12D";
    public static final String FLAT_1 = "14";
    public static final String TOWN_1 = "New York";
    public static final String ZIP_CODE_1 = "32-223";
    public static final String COUNTRY_1 = "USA";

    public static Address aAddress1() {
        return anObject(Address.class)
                .with(e -> e.setStreet(STREET_1))
                .with(e -> e.setNumber(NUMBER_1))
                .with(e -> e.setFlat(FLAT_1))
                .with(e -> e.setTown(TOWN_1))
                .with(e -> e.setZipCode(ZIP_CODE_1))
                .with(e -> e.setCountry(COUNTRY_1))
                .build();
    }

    public static AddressRest aAddressRest1() {
        return anObject(AddressRest.class)
                .with(e -> e.setStreet(STREET_1))
                .with(e -> e.setNumber(NUMBER_1))
                .with(e -> e.setFlat(FLAT_1))
                .with(e -> e.setTown(TOWN_1))
                .with(e -> e.setZipCode(ZIP_CODE_1))
                .with(e -> e.setCountry(COUNTRY_1))
                .build();
    }
}
