package org.carly.api.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Getter
@Setter
public class OrderResponse {
    private ObjectId id;
    private LocalDate createAt;
    private String status;
    private ObjectId userId;
    private ObjectId carId;
}
