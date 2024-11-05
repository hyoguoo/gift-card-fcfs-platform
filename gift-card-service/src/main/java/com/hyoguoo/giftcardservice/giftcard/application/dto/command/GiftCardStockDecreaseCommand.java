package com.hyoguoo.giftcardservice.giftcard.application.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardStockDecreaseCommand {

    private Long giftCardId;
    private Integer stockQuantity;
}
