package com.hyoguoo.orderservice.order.exception.common;

import com.hyoguoo.orderservice.core.common.exception.ErrorResponse;
import com.hyoguoo.orderservice.order.exception.OrderCompleteValidationException;
import com.hyoguoo.orderservice.order.exception.OrderFailValidationException;
import com.hyoguoo.orderservice.order.exception.OrderFoundException;
import com.hyoguoo.orderservice.order.exception.OrderPaymentException;
import com.hyoguoo.orderservice.order.exception.OrderValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderValidationException.class)
    public ResponseEntity<ErrorResponse> catchOrderValidationException(OrderValidationException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(OrderCompleteValidationException.class)
    public ResponseEntity<ErrorResponse> catchOrderCompleteValidationException(OrderCompleteValidationException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(OrderFailValidationException.class)
    public ResponseEntity<ErrorResponse> catchOrderFailValidationException(OrderFailValidationException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(OrderFoundException.class)
    public ResponseEntity<ErrorResponse> catchOrderFoundException(OrderFoundException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(OrderPaymentException.class)
    public ResponseEntity<ErrorResponse> catchOrderPaymentException(OrderPaymentException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }
}
