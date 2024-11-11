package com.hyoguoo.giftcardservice.giftcard.exception;

import com.hyoguoo.giftcardservice.giftcard.exception.common.GiftCardErrorCode;
import lombok.Getter;

@Getter
public class GiftCardUserFoundException extends RuntimeException {

    private final String code;
    private final String message;

    private GiftCardUserFoundException(GiftCardErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static GiftCardUserFoundException of(GiftCardErrorCode errorCode) {
        return new GiftCardUserFoundException(errorCode);
    }
}
