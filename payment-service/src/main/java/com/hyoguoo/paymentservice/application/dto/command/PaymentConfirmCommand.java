package com.hyoguoo.paymentservice.application.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentConfirmCommand {

    private final Long buyerId;
    private final String orderId;
    private final Long amount;
    private final String paymentKey;
}
