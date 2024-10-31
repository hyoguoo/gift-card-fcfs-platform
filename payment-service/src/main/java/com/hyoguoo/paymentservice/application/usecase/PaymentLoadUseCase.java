package com.hyoguoo.paymentservice.application.usecase;

import com.hyoguoo.paymentservice.application.port.PaymentEventRepository;
import com.hyoguoo.paymentservice.domain.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentLoadUseCase {

    private final PaymentEventRepository paymentEventRepository;

    public PaymentEvent loadPayment(String orderId) {
        return paymentEventRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }
}
