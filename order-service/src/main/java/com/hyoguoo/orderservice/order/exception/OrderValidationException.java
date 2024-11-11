package com.hyoguoo.orderservice.order.exception;

import com.hyoguoo.orderservice.order.exception.common.OrderErrorCode;
import lombok.Getter;

@Getter
public class OrderValidationException extends RuntimeException {

    private final String code;
    private final String message;

    private OrderValidationException(OrderErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static OrderValidationException of(OrderErrorCode errorCode) {
        return new OrderValidationException(errorCode);
    }
}
