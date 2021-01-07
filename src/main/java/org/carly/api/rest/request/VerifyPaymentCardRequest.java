package org.carly.api.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyPaymentCardRequest {
    private String paymentCardId;
    private String cvvCode;
}
