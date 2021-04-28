package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.carly.api.rest.request.FactoryRequest;
import org.carly.core.companymanagement.service.CompanyMatchingService;
import org.carly.core.companymanagement.service.FactoryService;
import org.carly.core.ordermanagement.model.cart.PartType;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/factory")
public class FactoryController {

    private static final String GET_FACTORY_BY_ID = "Get factory by id";
    private static final String GET_PART_DETAILS = "Get part details";

    private final FactoryService factoryService;

    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @PostMapping("/get-factory-details")
    @ApiOperation(value = GET_FACTORY_BY_ID)
    public ResponseEntity<?> getFactoryById(@RequestBody FactoryRequest factoryRequest) {
        return factoryService.getFactoryById(factoryRequest);
    }

    @GetMapping("/get-part-details/{partId}")
    @ApiOperation(value = GET_PART_DETAILS)
    public ResponseEntity<?> getPartDetails(@PathVariable(value = "partId") String partId,
                                            @Param(value = "partType") PartType partType) {
        return factoryService.getPartDetailsById(partId, partType);
    }
}
