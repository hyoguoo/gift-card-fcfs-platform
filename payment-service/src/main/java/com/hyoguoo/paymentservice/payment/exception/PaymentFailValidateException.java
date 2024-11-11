package com.hyoguoo.paymentservice.payment.exception;

import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import lombok.Getter;

@Getter
public class PaymentFailValidateException extends RuntimeException {

    private final String code;
    private final String message;

    private PaymentFailValidateException(PaymentErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static PaymentFailValidateException of(PaymentErrorCode errorCode) {
        return new PaymentFailValidateException(errorCode);
    }
}
