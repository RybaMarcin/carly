package org.carly.api.endpoint;

import org.carly.core.customermanagement.service.CustomerFindService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api//customer")
public class CustomerController {

    private final CustomerFindService customerFindService;

    public CustomerController(CustomerFindService customerFindService) {
        this.customerFindService = customerFindService;
    }

}
