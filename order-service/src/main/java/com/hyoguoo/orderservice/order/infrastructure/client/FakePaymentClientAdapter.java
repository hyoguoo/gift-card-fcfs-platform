package com.hyoguoo.orderservice.order.infrastructure.client;

import com.hyoguoo.orderservice.order.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.orderservice.order.application.port.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FakePaymentClientAdapter implements PaymentPort {

    @Override
    public void checkoutPayment(PaymentCheckoutCommand command) {
        // Do nothing
    }
}
