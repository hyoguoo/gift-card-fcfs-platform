package com.hyoguoo.paymentservice.payment.application.dto.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentConfirmResult {

    private final String orderId;
    private final Long amount;
}
