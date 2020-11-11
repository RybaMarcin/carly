package org.carly.core.usermanagement.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRest {
    private ObjectId id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String email;
    @NotNull
    private String gender;
}
