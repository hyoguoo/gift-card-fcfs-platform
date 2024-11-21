package com.hyoguoo.paymentservice.payment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentConfirmResponse {

    private final String orderId;
    private final Long amount;
}
