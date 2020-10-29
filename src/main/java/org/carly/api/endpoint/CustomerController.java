package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.response.CustomerResponse;
import org.carly.core.customermanagement.service.CustomerFindService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/customers")
public class CustomerController {

    private final CustomerFindService customerFindService;

    public CustomerController(CustomerFindService customerFindService) {
        this.customerFindService = customerFindService;
    }

    @GetMapping("/customer-by-id/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public CustomerResponse findCustomer(@PathVariable("id")String  carId) {
        return customerFindService.findCustomerByCarId(new ObjectId(carId));
    }
}
