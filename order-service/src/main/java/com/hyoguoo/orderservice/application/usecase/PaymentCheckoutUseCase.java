package com.hyoguoo.orderservice.application.usecase;

import com.hyoguoo.orderservice.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.orderservice.application.port.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCheckoutUseCase {

    private final PaymentPort paymentPort;

    public void checkoutPayment(PaymentCheckoutCommand command) {
        paymentPort.checkoutPayment(command);
    }
}
