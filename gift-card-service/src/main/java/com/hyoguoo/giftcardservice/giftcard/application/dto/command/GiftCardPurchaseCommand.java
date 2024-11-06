package com.hyoguoo.giftcardservice.giftcard.application.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardPurchaseCommand {

    private final Long giftCardId;
    private final Long userId;
}
