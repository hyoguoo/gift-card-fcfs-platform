package com.hyoguoo.paymentservice.payment.exception;

import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import lombok.Getter;

@Getter
public class PaymentConfirmationException extends Exception {

    private final String code;
    private final String message;

    private PaymentConfirmationException(PaymentErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static PaymentConfirmationException of(PaymentErrorCode errorCode) {
        return new PaymentConfirmationException(errorCode);
    }
}
