package org.carly.core.usermanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.carly.core.companymanagement.model.Company;
import org.carly.core.customermanagement.model.Customer;
import org.carly.core.security.model.CarlyGrantedAuthority;
import org.carly.core.shared.model.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Document(collection = "users")
@NoArgsConstructor
public class User {

    public static final String ID = "id";
    public static final String COMPANY_FIELD_NAME = "company";
    @Id
    @Field(ID)
    private ObjectId id;
    private List<CarlyGrantedAuthority> roles;
    @Field(COMPANY_FIELD_NAME)
    private Company company;
    private Customer customer;
    private String phoneNumber;
    private String email;
    private Address address;
    private List<Address> addressHistory;
    private String password;
    private LocalDate createdAt;
    private Boolean enabled;

    public User(Customer customer, String email, String phone, String password){
        this.customer = customer;
        this.email = email;
        this.phoneNumber = phone;
        this.password = password;
    }

    public User(Company company, String email, String phoneNumber, String password) {
        this.company = company;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}