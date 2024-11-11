package com.hyoguoo.giftcardservice.giftcard.exception;

import com.hyoguoo.giftcardservice.giftcard.exception.common.GiftCardErrorCode;
import lombok.Getter;

@Getter
public class GiftCardValidationException extends RuntimeException {

    private final String code;
    private final String message;

    private GiftCardValidationException(GiftCardErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static GiftCardValidationException of(GiftCardErrorCode errorCode) {
        return new GiftCardValidationException(errorCode);
    }
}
