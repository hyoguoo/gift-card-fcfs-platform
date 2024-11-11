package com.hyoguoo.orderservice.order.exception;

import com.hyoguoo.orderservice.order.exception.common.OrderErrorCode;
import lombok.Getter;

@Getter
public class OrderFoundException extends RuntimeException {

    private final String code;
    private final String message;

    private OrderFoundException(OrderErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static OrderFoundException of(OrderErrorCode errorCode) {
        return new OrderFoundException(errorCode);
    }
}
