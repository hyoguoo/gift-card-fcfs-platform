package com.hyoguoo.paymentservice.payment.exception;

import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import lombok.Getter;

@Getter
public class PaymentExecuteValidateException extends RuntimeException {

    private final String code;
    private final String message;

    private PaymentExecuteValidateException(PaymentErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static PaymentExecuteValidateException of(PaymentErrorCode errorCode) {
        return new PaymentExecuteValidateException(errorCode);
    }
}
