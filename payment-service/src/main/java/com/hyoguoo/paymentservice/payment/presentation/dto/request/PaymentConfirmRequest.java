package com.hyoguoo.paymentservice.payment.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentConfirmRequest {

    private final String orderId;
    private final Long amount;
    private final String paymentKey;
}
