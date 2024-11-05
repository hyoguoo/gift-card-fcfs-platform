package com.hyoguoo.giftcardservice.giftcard.application.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindMyGiftCardCursorCommand {

    private final Long userId;
    private final Long cursor;
    private final Long size;
}
