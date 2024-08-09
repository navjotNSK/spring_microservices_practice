package com.navjot.order_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.navjot.order_service.common.TransactionRequest;
import com.navjot.order_service.common.TransactionResponse;
import com.navjot.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;
    
    @PostMapping("/bookOrder")
    @CircuitBreaker(name="Order-Service" , fallbackMethod = "orderServiceFallBack1")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
        return service.saveOrder(request);
    }

    public TransactionResponse orderServiceFallBack1(Exception e) {
        return TransactionResponse.builder().transactionId("101").build();
    }

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceFallBack(Exception e) {
        return Mono.just("Order Service is taking too long to respond or is down. Please try again later");
    }
}
