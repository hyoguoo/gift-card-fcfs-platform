package com.hyoguoo.paymentservice.application.port;

import com.hyoguoo.paymentservice.application.dto.command.TossPaymentConfirmCommand;
import com.hyoguoo.paymentservice.domain.dto.TossPaymentInfo;

public interface TossPaymentGateway {

    TossPaymentInfo getPaymentInfoByOrderId(String orderId);

    TossPaymentInfo confirmPayment(TossPaymentConfirmCommand tossConfirmGatewayCommand, String idempotencyKey);
}

