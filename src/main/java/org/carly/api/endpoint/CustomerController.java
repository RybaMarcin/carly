package org.carly.api.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.CustomerRest;
import org.carly.core.customermanagement.service.CustomerFindService;
import org.carly.core.customermanagement.service.CustomerSaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/customers")
@Slf4j
public class CustomerController {

    private final CustomerFindService customerFindService;
    private final CustomerSaveService customerSaveService;

    public CustomerController(CustomerFindService customerFindService, CustomerSaveService customerSaveService) {
        this.customerFindService = customerFindService;
        this.customerSaveService = customerSaveService;
    }

    @GetMapping("/customer-by-id/{id}")
    public CustomerRest findCustomer(@PathVariable("id")String  carId) {
        return customerFindService.findCustomerByCarId(new ObjectId(carId));
    }
}
