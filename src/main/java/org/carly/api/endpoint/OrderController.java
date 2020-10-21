package org.carly.api.endpoint;

import org.bson.types.ObjectId;
import org.carly.api.rest.OrderRest;
import org.carly.core.ordermanagement.service.OrderFindService;
import org.carly.core.ordermanagement.service.OrderSaveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("order")
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
    public OrderRest findOrderById(@PathVariable("id") String orderId) {
        return orderFindService.findOrderById(new ObjectId(orderId));
    }

    @PostMapping("/save")
    public ObjectId saveOrder(@RequestBody OrderRest orderRest) {
        return orderSaveService.save(orderRest);
    }

    @PutMapping("/update")
    public ResponseEntity updateOrder(@RequestBody OrderRest orderRest) {
        return orderSaveService.updateOrder(orderRest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") String orderId) {
        return orderSaveService.deleteOrder(new ObjectId(orderId));
    }

    @GetMapping("/findAllOrdersByCustomerId/{id}")
    public Page<OrderRest> findAllOrdersByCustomer(@PathVariable("id") String customerId,
                                                   Pageable pageable) {
        return orderFindService.findByCustomerId(new ObjectId(customerId), pageable);
    }
}