package com.hyoguoo.paymentservice.payment.exception;

import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import lombok.Getter;

@Getter
public class PaymentLockException extends RuntimeException {

    private final String code;
    private final String message;

    private PaymentLockException(PaymentErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static PaymentLockException of(PaymentErrorCode errorCode) {
        return new PaymentLockException(errorCode);
    }
}
