package com.hyoguoo.paymentservice.payment.application.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TossPaymentConfirmCommand {

    private final String orderId;
    private final Long amount;
    private final String paymentKey;
    private final String idempotencyKey;
}
