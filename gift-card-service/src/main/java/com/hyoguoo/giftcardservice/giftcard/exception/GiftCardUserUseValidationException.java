package com.hyoguoo.giftcardservice.giftcard.exception;

import com.hyoguoo.giftcardservice.giftcard.exception.common.GiftCardErrorCode;
import lombok.Getter;

@Getter
public class GiftCardUserUseValidationException extends RuntimeException {

    private final String code;
    private final String message;

    private GiftCardUserUseValidationException(GiftCardErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static GiftCardUserUseValidationException of(GiftCardErrorCode errorCode) {
        return new GiftCardUserUseValidationException(errorCode);
    }
}
