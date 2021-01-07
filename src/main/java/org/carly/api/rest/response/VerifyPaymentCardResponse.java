package org.carly.api.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyPaymentCardResponse {
    private String message;
    private boolean isPaymentCardVerified;
}
