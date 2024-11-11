package com.hyoguoo.giftcardservice.giftcard.exception;

import com.hyoguoo.giftcardservice.giftcard.exception.common.GiftCardErrorCode;
import lombok.Getter;

@Getter
public class GiftCardUserValidationException extends RuntimeException {

    private final String code;
    private final String message;

    private GiftCardUserValidationException(GiftCardErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static GiftCardUserValidationException of(GiftCardErrorCode errorCode) {
        return new GiftCardUserValidationException(errorCode);
    }
}
