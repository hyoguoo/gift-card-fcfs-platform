package com.hyoguoo.orderservice.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardInfo {

    private final Long giftCardId;
    private final String giftCardName;
    private final Long price;
}
