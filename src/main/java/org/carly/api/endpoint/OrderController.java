package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.response.OrderResponse;
import org.carly.core.ordermanagement.service.OrderFindService;
import org.carly.core.ordermanagement.service.OrderSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/order")
@RestController
public class OrderController {

    private final OrderFindService orderFindService;
    private final OrderSaveService orderSaveService;

    public OrderController(OrderFindService orderFindService,
                           OrderSaveService orderSaveService) {
        this.orderFindService = orderFindService;
        this.orderSaveService = orderSaveService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public OrderResponse findOrderById(@PathVariable("id") String orderId) {
        return orderFindService.findOrderById(new ObjectId(orderId));
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public ObjectId saveOrder(@RequestBody OrderResponse orderResponse) {
        return orderSaveService.save(orderResponse);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public ResponseEntity updateOrder(@RequestBody OrderResponse orderResponse) {
        return orderSaveService.updateOrder(orderResponse);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public ResponseEntity deleteOrder(@PathVariable("id") String orderId) {
        return orderSaveService.deleteOrder(new ObjectId(orderId));
    }

    @GetMapping("/find-all-orders-by-customer-id/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'CARLY_COMPANY', 'CARLY_CUSTOMER')")
    public Page<OrderResponse> findAllOrdersByCustomer(@PathVariable("id") String customerId,
                                                       Pageable pageable) {
        return orderFindService.findByCustomerId(new ObjectId(customerId), pageable);
    }
}