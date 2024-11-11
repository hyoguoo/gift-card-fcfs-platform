package com.hyoguoo.giftcardservice.giftcard.exception.common;

import com.hyoguoo.giftcardservice.core.common.exception.ErrorResponse;
import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardFoundException;
import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardUserFoundException;
import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardUserUseValidationException;
import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardUserValidationException;
import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardValidationException;
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
public class GiftCardExceptionHandler {

    @ExceptionHandler(GiftCardFoundException.class)
    public ResponseEntity<ErrorResponse> catchRuntimeException(GiftCardFoundException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(GiftCardUserFoundException.class)
    public ResponseEntity<ErrorResponse> catchRuntimeException(GiftCardUserFoundException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(GiftCardValidationException.class)
    public ResponseEntity<ErrorResponse> catchRuntimeException(GiftCardValidationException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(GiftCardUserValidationException.class)
    public ResponseEntity<ErrorResponse> catchRuntimeException(GiftCardUserValidationException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }

    @ExceptionHandler(GiftCardUserUseValidationException.class)
    public ResponseEntity<ErrorResponse> catchRuntimeException(GiftCardUserUseValidationException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                                e.getCode(),
                                e.getMessage()
                        )
                );
    }
}
