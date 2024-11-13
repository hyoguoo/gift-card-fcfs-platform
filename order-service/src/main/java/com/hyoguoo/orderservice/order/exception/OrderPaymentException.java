package com.hyoguoo.orderservice.order.exception;

import com.hyoguoo.orderservice.order.exception.common.OrderErrorCode;
import lombok.Getter;

@Getter
public class OrderPaymentException extends RuntimeException {

    private final String code;
    private final String message;

    private OrderPaymentException(OrderErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static OrderPaymentException of(OrderErrorCode errorCode) {
        return new OrderPaymentException(errorCode);
    }
}
