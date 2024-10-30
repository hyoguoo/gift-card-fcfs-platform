package com.hyoguoo.giftcardservice.application.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardUseCommand {

    private final Long userGiftCardId;
    private final Long usedUserId;
    private final Long usedAmount;
}
