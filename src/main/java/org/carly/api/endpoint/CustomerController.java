package org.carly.api.endpoint;

import org.carly.core.customermanagement.service.CustomerFindService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/customer")
public class CustomerController {

    private final CustomerFindService customerFindService;

    public CustomerController(CustomerFindService customerFindService) {
        this.customerFindService = customerFindService;
    }

}
