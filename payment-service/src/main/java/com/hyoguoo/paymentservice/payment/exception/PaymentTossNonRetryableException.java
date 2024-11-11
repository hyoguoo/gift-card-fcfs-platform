package com.hyoguoo.paymentservice.payment.exception;

import com.hyoguoo.paymentservice.payment.domain.dto.vo.TossPaymentFailure;
import lombok.Getter;

@Getter
public class PaymentTossNonRetryableException extends Exception {

    private final String code;
    private final String message;

    private PaymentTossNonRetryableException(TossPaymentFailure tossPaymentFailure) {
        this.code = tossPaymentFailure.getCode();
        this.message = tossPaymentFailure.getMessage();
    }

    public static PaymentTossNonRetryableException of(TossPaymentFailure tossPaymentFailure) {
        return new PaymentTossNonRetryableException(tossPaymentFailure);
    }
}
