package com.hyoguoo.orderservice.order.exception;

import com.hyoguoo.orderservice.order.exception.common.OrderErrorCode;
import lombok.Getter;

@Getter
public class OrderCompleteValidationException extends RuntimeException {

    private final String code;
    private final String message;

    private OrderCompleteValidationException(OrderErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static OrderCompleteValidationException of(OrderErrorCode errorCode) {
        return new OrderCompleteValidationException(errorCode);
    }
}
