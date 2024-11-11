package com.hyoguoo.paymentservice.payment.application.usecase;

import com.hyoguoo.paymentservice.payment.application.port.PaymentEventRepository;
import com.hyoguoo.paymentservice.payment.domain.PaymentEvent;
import com.hyoguoo.paymentservice.payment.exception.PaymentFoundException;
import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentLoadUseCase {

    private final PaymentEventRepository paymentEventRepository;

    public PaymentEvent loadPayment(String orderId) {
        return paymentEventRepository.findByOrderId(orderId)
                .orElseThrow(() -> PaymentFoundException.of(PaymentErrorCode.PAYMENT_NOT_FOUND));
    }
}
