package com.hyoguoo.orderservice.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/order/hello")
    public String hello() {
        return "Hello, Order!";
    }
}
