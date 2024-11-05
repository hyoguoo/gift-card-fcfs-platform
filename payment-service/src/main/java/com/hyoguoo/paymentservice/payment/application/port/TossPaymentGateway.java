package com.hyoguoo.paymentservice.payment.application.port;

import com.hyoguoo.paymentservice.payment.application.dto.command.TossPaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.domain.dto.TossPaymentInfo;

public interface TossPaymentGateway {

    TossPaymentInfo getPaymentInfoByOrderId(String orderId);

    TossPaymentInfo confirmPayment(TossPaymentConfirmCommand tossConfirmGatewayCommand, String idempotencyKey);
}

