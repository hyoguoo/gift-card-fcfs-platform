package com.hyoguoo.giftcardservice.application.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateNewGiftCardCommand {

    private final String giftCardName;
    private final Long quantity;
    private final Long price;
}
