package org.carly.api.endpoint;

import io.swagger.annotations.ApiOperation;
import org.carly.api.rest.request.PartToCartRequest;
import org.carly.core.ordermanagement.service.CartKeeperService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/cart")
@RestController
public class CartController {

    private static final String ADD_PART_TO_CART = "Add part to card or modify exists one";
    private static final String FIND_ALL_CART_PARTS_BY_CONSUMER_ID = "Find all consumer cart parts by id";
    private static final String FIND_ALL_CARTS = "Find all available carts";
    private final CartKeeperService cartKeeperService;

    public CartController(CartKeeperService cartKeeperService) {
        this.cartKeeperService = cartKeeperService;
    }

    @PostMapping("/update-cart")
    @ApiOperation(value = ADD_PART_TO_CART)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public ResponseEntity<?> addPartToCartOrUpdate(@RequestBody PartToCartRequest request) {
        return cartKeeperService.addOrUpdatePartToCart(request);
    }

    @GetMapping("/get-cart-factories/{consumerId}")
    @ApiOperation(value = FIND_ALL_CART_PARTS_BY_CONSUMER_ID)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public ResponseEntity<?> getCartFactoriesInfo(@PathVariable(value = "consumerId") String id) {
        return cartKeeperService.getCartFactoriesInfoByConsumerId(id);
    }

    @GetMapping("/get-all-carts")
    @ApiOperation(value = FIND_ALL_CARTS)
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> getAllAvailableCarts(){
        return cartKeeperService.getAllAvailableCarts();
    }
}
