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
    public OrderRest findOrderById(@PathVariable("id") ObjectId orderId) {
        return orderFindService.findOrderById(orderId);
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
    public ResponseEntity deleteOrder(@PathVariable("id") ObjectId orderId){
       return orderSaveService.deleteOrder(orderId);
    }

    @GetMapping("/findAllOrdersByCustomerId/{id}")
    public Page<OrderRest> findAllOrdersByCustomer(@PathVariable("id") ObjectId customerId,
                                                   Pageable pageable){
       return orderFindService.findByCustomerId(customerId, pageable);
    }
}