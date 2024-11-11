package com.hyoguoo.paymentservice.payment.exception;

import com.hyoguoo.paymentservice.payment.domain.dto.vo.TossPaymentFailure;
import lombok.Getter;

@Getter
public class PaymentTossRetryableException extends Exception {

    private final String code;
    private final String message;

    private PaymentTossRetryableException(TossPaymentFailure tossPaymentFailure) {
        this.code = tossPaymentFailure.getCode();
        this.message = tossPaymentFailure.getMessage();
    }

    public static PaymentTossRetryableException of(TossPaymentFailure tossPaymentFailure) {
        return new PaymentTossRetryableException(tossPaymentFailure);
    }
}
