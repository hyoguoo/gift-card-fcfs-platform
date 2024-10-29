package com.hyoguoo.giftcardservice.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiftCardController {

    @GetMapping("/gift-card/hello")
    public String hello() {
        return "Hello, Gift Card!";
    }

    @GetMapping("/gift-card/auth")
    public String auth(@RequestHeader(value = "X-USER-ID") Long userId) {
        System.out.println("userId = " + userId);
        return "Auth";
    }
}
