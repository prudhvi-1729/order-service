package com.capstone.order_service.controller;

import com.capstone.order_service.dto.OrderRequest;
import com.capstone.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        boolean success = orderService.placeOrder(orderRequest);
        if(success){
            return "Order successfully created";
        }
        else {
            return "Stock not available";
        }
    }

}
