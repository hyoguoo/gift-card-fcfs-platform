package com.hyoguoo.paymentservice.payment.application;

import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.paymentservice.payment.application.usecase.PaymentCreateUseCase;
import com.hyoguoo.paymentservice.payment.presentation.port.PaymentCheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCheckoutServiceImpl implements PaymentCheckoutService {

    private final PaymentCreateUseCase paymentCreateUseCase;

    @Override
    public void checkoutPayment(PaymentCheckoutCommand command) {
        paymentCreateUseCase.createPayment(command);
    }
}
