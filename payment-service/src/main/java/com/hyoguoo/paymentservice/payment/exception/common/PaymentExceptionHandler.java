package com.hyoguoo.paymentservice.payment.exception.common;

import com.hyoguoo.paymentservice.core.common.exception.ErrorResponse;
import com.hyoguoo.paymentservice.payment.exception.PaymentConfirmException;
import com.hyoguoo.paymentservice.payment.exception.PaymentConfirmationException;
import com.hyoguoo.paymentservice.payment.exception.PaymentDoneValidateException;
import com.hyoguoo.paymentservice.payment.exception.PaymentExecuteValidateException;
import com.hyoguoo.paymentservice.payment.exception.PaymentFailValidateException;
import com.hyoguoo.paymentservice.payment.exception.PaymentFoundException;
import com.hyoguoo.paymentservice.payment.exception.PaymentLockException;
import com.hyoguoo.paymentservice.payment.exception.PaymentTossNonRetryableException;
import com.hyoguoo.paymentservice.payment.exception.PaymentTossRetryableException;
import com.hyoguoo.paymentservice.payment.exception.PaymentUnknownValidateException;
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
public class PaymentExceptionHandler {

    @ExceptionHandler(PaymentFoundException.class)
    public ResponseEntity<ErrorResponse> catchPaymentFoundException(PaymentFoundException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(PaymentConfirmException.class)
    public ResponseEntity<ErrorResponse> catchPaymentConfirmException(PaymentConfirmException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(PaymentExecuteValidateException.class)
    public ResponseEntity<ErrorResponse> catchPaymentExecuteValidateException(PaymentExecuteValidateException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(PaymentFailValidateException.class)
    public ResponseEntity<ErrorResponse> catchPaymentFailValidateException(PaymentFailValidateException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(PaymentUnknownValidateException.class)
    public ResponseEntity<ErrorResponse> catchPaymentUnknownValidateException(PaymentUnknownValidateException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(PaymentConfirmationException.class)
    public ResponseEntity<ErrorResponse> catchPaymentConfirmationException(PaymentConfirmationException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(PaymentDoneValidateException.class)
    public ResponseEntity<ErrorResponse> catchPaymentDoneValidateException(PaymentDoneValidateException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(PaymentTossNonRetryableException.class)
    public ResponseEntity<ErrorResponse> catchPaymentTossNonRetryableException(PaymentTossNonRetryableException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(PaymentTossRetryableException.class)
    public ResponseEntity<ErrorResponse> catchPaymentTossRetryableException(PaymentTossRetryableException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(PaymentLockException.class)
    public ResponseEntity<ErrorResponse> catchPaymentLockException(PaymentLockException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }
}
