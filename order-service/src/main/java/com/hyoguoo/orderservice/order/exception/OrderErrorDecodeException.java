package com.hyoguoo.orderservice.order.exception;

import com.hyoguoo.orderservice.order.exception.common.OrderErrorCode;
import lombok.Getter;

@Getter
public class OrderErrorDecodeException extends RuntimeException {

    private final String code;
    private final String message;

    private OrderErrorDecodeException(OrderErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static OrderErrorDecodeException of(OrderErrorCode errorCode) {
        return new OrderErrorDecodeException(errorCode);
    }
}
