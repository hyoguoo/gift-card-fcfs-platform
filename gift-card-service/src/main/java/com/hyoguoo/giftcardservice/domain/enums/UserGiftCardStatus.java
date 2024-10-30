package com.hyoguoo.giftcardservice.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserGiftCardStatus {

    ACTIVE("ACTIVE"),
    USING("USING"),
    USED("USED"),
    EXPIRED("EXPIRED"),
    CANCELED("CANCELED");

    private final String status;
}
