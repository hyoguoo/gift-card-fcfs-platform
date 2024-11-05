package com.hyoguoo.giftcardservice.giftcard.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardUseRequest {

    private final Long userGiftCardId;
    private final Long usedAmount;
}
