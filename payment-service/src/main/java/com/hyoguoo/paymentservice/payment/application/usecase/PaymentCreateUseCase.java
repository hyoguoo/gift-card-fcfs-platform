package com.hyoguoo.paymentservice.payment.application.usecase;

import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.paymentservice.payment.application.port.PaymentEventRepository;
import com.hyoguoo.paymentservice.payment.domain.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCreateUseCase {

    private final PaymentEventRepository paymentEventRepository;

    public void createPayment(PaymentCheckoutCommand command) {
        PaymentEvent paymentEvent = PaymentEvent.requiredBuilder()
                .command(command)
                .requiredBuild();

        paymentEventRepository.saveOrUpdate(paymentEvent);
    }
}
