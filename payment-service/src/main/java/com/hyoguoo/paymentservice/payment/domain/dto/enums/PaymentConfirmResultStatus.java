package com.hyoguoo.paymentservice.payment.domain.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentConfirmResultStatus {
    SUCCESS("SUCCESS"),
    RETRYABLE_FAILURE("RETRYABLE_FAILURE"),
    NON_RETRYABLE_FAILURE("NON_RETRYABLE_FAILURE"),
    ;

    private final String value;
}

