package org.carly.api.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyMatchingRequest {

    private String companyId;
    private String factoryId;
    private String message;
}
