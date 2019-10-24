package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.shared.security.model.CarlyGrantedAuthority;
import org.carly.shared.security.model.UserRole;
import org.carly.user_management.api.model.UserRest;
import org.carly.user_management.core.model.Gender;
import org.carly.user_management.core.model.User;
import org.carly.vehicle_management.core.model.Car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.carly.shared.utils.builder.Builder.anObject;
import static org.carly.support.AddressesModel.aAddress1;
import static org.carly.support.Cars.aCar1;

public class Users {

    public static final ObjectId USER_ID_1 = new ObjectId("5da1cd6cbe0ad871080c2622");
    public static final List<CarlyGrantedAuthority> ROLES_1 = roleList();
    public static final String CODE_1 = "f307ba";
    public static final String FIRST_NAME_1 = "John";
    public static final String LAST_NAME_1 = "Wick";
    public static final String PHONE_NUMBER_1 = "888777555";
    public static final String EMAIL_1 = "john.wick@pencil.com";
    public static final List<Car> CARS_1 = List.of(aCar1());
    public static final Gender GENDER_1 = Gender.MALE;
    public static final LocalDate CREATED_AT_1 = LocalDate.of(2017, 2, 22);
    public static final boolean ENABLED_1 = true;

    private static List<CarlyGrantedAuthority> roleList() {
        List<CarlyGrantedAuthority> roles = new ArrayList<>();
        roles.add(CarlyGrantedAuthority.of(UserRole.CUSTOMER.name()));
        roles.add(CarlyGrantedAuthority.of(UserRole.CHANGE_PASSWORD_PRIVILEGE.name()));
        return roles;
    }

    public static User aUser1() {
        return anObject(User.class)
                .with(u -> u.setId(USER_ID_1))
                .with(u -> u.setRole(ROLES_1))
                .with(u -> u.setCode(CODE_1))
                .with(u -> u.setFirstName(FIRST_NAME_1))
                .with(u -> u.setLastName(LAST_NAME_1))
                .with(u -> u.setPhoneNumber(PHONE_NUMBER_1))
                .with(u -> u.setEmail(EMAIL_1))
                .with(u -> u.setCars(CARS_1))
                .with(u -> u.setGender(GENDER_1))
                .with(u -> u.setCreatedAt(CREATED_AT_1))
                .with(u -> u.setEnabled(ENABLED_1))
                .build();
    }

    public static UserRest aUserRest1() {
        return anObject(UserRest.class)
                .with(u -> u.setId(USER_ID_1))
                .with(u -> u.setFirstName(FIRST_NAME_1))
                .with(u -> u.setLastName(LAST_NAME_1))
                .with(u -> u.setPhoneNumber(PHONE_NUMBER_1))
                .with(u -> u.setEmail(EMAIL_1))
                .with(u -> u.setGender(GENDER_1.name()))
                .build();
    }

    public static User aUserWithAddress1() {
        return anObject(User.class)
                .with(u -> u.setId(USER_ID_1))
                .with(u -> u.setRole(ROLES_1))
                .with(u -> u.setCode(CODE_1))
                .with(u -> u.setFirstName(FIRST_NAME_1))
                .with(u -> u.setLastName(LAST_NAME_1))
                .with(u -> u.setPhoneNumber(PHONE_NUMBER_1))
                .with(u -> u.setEmail(EMAIL_1))
                .with(u -> u.setCars(CARS_1))
                .with(u -> u.setGender(GENDER_1))
                .with(u -> u.setCreatedAt(CREATED_AT_1))
                .with(u -> u.setAddress(aAddress1()))
                .with(u -> u.setEnabled(ENABLED_1))
                .build();
    }

}
