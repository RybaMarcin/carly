package org.carly.api.rest.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentCardResponse {
    private String id;
    private String userId;
    private String paymentCardProvider;
    private String paymentCardHolder;
    private String paymentCardNumber;
    private LocalDate expiryDate;
    private String cvvCode;
    private boolean cardActive;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
