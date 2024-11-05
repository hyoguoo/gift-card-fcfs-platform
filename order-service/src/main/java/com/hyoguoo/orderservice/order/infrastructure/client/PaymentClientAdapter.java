package com.hyoguoo.orderservice.order.infrastructure.client;

import com.hyoguoo.orderservice.order.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.orderservice.order.application.port.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentClientAdapter implements PaymentPort {

    private final PaymentFeignClient paymentFeignClient;

    @Override
    public void checkoutPayment(PaymentCheckoutCommand command) {
        paymentFeignClient.checkoutPayment(command);
    }
}
