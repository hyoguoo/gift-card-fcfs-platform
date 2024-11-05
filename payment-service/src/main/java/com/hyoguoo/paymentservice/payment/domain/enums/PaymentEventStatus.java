package com.hyoguoo.paymentservice.payment.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentEventStatus {

    READY("READY"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    FAILED("FAILED"),
    CANCELED("CANCELED"),
    PARTIAL_CANCELED("PARTIAL_CANCELED"),
    UNKNOWN("UNKNOWN"),
    ;

    private final String value;
}
