package com.hyoguoo.paymentservice.payment.exception;

import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import lombok.Getter;

@Getter
public class PaymentUnknownValidateException extends RuntimeException {

    private final String code;
    private final String message;

    private PaymentUnknownValidateException(PaymentErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static PaymentUnknownValidateException of(PaymentErrorCode errorCode) {
        return new PaymentUnknownValidateException(errorCode);
    }
}
