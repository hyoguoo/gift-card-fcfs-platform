package com.hyoguoo.paymentservice.payment.exception;

import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import lombok.Getter;

@Getter
public class PaymentDoneValidateException extends Exception {

    private final String code;
    private final String message;

    private PaymentDoneValidateException(PaymentErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static PaymentDoneValidateException of(PaymentErrorCode errorCode) {
        return new PaymentDoneValidateException(errorCode);
    }
}
