package org.carly.core.paymentcardmanagement.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "payment-cards")
public class PaymentCard {

    public static final String ID = "_id";
    public static final String USER_ID = "userId";
    public static final String PAYMENT_CARD_PROVIDER = "paymentCardProvider";
    public static final String PAYMENT_CARD_HOLDER = "paymentCardHolder";
    public static final String PAYMENT_CARD_NUMBER = "paymentCardNumber";
    public static final String EXPIRY_DATE = "expiryDate";
    public static final String CVV_CODE = "cvvCode";
    public static final String CARD_ACTIVE = "cardActive";
    public static final String CREATED_DATE = "createdDate";
    public static final String MODIFIED_DATE = "modifiedDate";

    @Id
    @Field(ID)
    private ObjectId id;

    @Field(USER_ID)
    private String userId;

    @Field(PAYMENT_CARD_PROVIDER)
    private String paymentCardProvider;

    @Field(PAYMENT_CARD_HOLDER)
    private String paymentCardHolder;

    @Field(PAYMENT_CARD_NUMBER)
    private String paymentCardNumber;

    @Field(EXPIRY_DATE)
    private LocalDate expiryDate;

    @Field(CVV_CODE)
    private String cvvCode;

    @Field(CARD_ACTIVE)
    private boolean cardActive;

    @Field(CREATED_DATE)
    private LocalDateTime createdDate;

    @Field(MODIFIED_DATE)
    private LocalDateTime modifiedDate;

}
