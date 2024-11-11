package com.hyoguoo.giftcardservice.giftcard.exception;

import com.hyoguoo.giftcardservice.giftcard.exception.common.GiftCardErrorCode;
import lombok.Getter;

@Getter
public class GiftCardFoundException extends RuntimeException {

    private final String code;
    private final String message;

    private GiftCardFoundException(GiftCardErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static GiftCardFoundException of(GiftCardErrorCode errorCode) {
        return new GiftCardFoundException(errorCode);
    }
}
