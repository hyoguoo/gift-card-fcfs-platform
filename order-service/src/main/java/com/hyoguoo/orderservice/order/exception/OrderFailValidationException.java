package com.hyoguoo.orderservice.order.exception;

import com.hyoguoo.orderservice.order.exception.common.OrderErrorCode;
import lombok.Getter;

@Getter
public class OrderFailValidationException extends RuntimeException {

    private final String code;
    private final String message;

    private OrderFailValidationException(OrderErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static OrderFailValidationException of(OrderErrorCode errorCode) {
        return new OrderFailValidationException(errorCode);
    }
}
