package org.carly.core.vehiclemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Factory {
    private ObjectId carlyFactoryId;
    private String name;
    private double rating;
}
