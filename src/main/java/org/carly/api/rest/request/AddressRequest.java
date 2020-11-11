package org.carly.api.rest.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddressRequest {
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotBlank
    private String flat;
    @NotBlank
    private String town;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String country;
}
