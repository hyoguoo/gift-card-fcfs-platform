package com.hyoguoo.couponservice.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

    @GetMapping("/coupon/hello")
    public String hello() {
        return "Hello, Coupon!";
    }

    @GetMapping("/coupon/auth")
    public String auth(@RequestHeader(value = "X-USER-ID") Long userId) {
        System.out.println("userId = " + userId);
        return "Auth";
    }
}
