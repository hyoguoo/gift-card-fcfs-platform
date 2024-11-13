package com.hyoguoo.orderservice.order.infrastructure.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentClientConfig {

    @Bean
    public ErrorDecoder paymentErrorDecoder() {
        return new PaymentErrorDecoder();
    }
}
