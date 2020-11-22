package org.carly.core.companymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.core.usermanagement.model.User;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company extends User {

    public static final String NAME = "name";

    @Field(NAME)
    private String name;

}
