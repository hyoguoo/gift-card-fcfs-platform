package com.hyoguoo.orderservice.order.infrastructure.client;

import com.hyoguoo.orderservice.order.application.dto.command.PaymentCheckoutCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", configuration = PaymentClientConfig.class)
public interface PaymentFeignClient {

    @PostMapping("/internal/payment/checkout")
    void checkoutPayment(@RequestBody PaymentCheckoutCommand command);
}
