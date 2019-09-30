package org.carly.customer_management.controller;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.customer_management.api.CustomerRest;
import org.carly.customer_management.service.CustomerFindService;
import org.carly.customer_management.service.CustomerSaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RestController("/customers")
@Slf4j
public class CustomerController {

    private final CustomerFindService customerFindService;
    private final CustomerSaveService customerSaveService;

    public CustomerController(CustomerFindService customerFindService, CustomerSaveService customerSaveService) {
        this.customerFindService = customerFindService;
        this.customerSaveService = customerSaveService;
    }

    @GetMapping("/{id}")
    public CustomerRest findCustomer(@PathVariable("id") ObjectId carId) {
        return customerFindService.findCustomerByCarId(carId);
    }
}
