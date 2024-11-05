package com.hyoguoo.orderservice.order.domain.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardInfo {

    private final Long giftCardId;
    private final String giftCardName;
    private final Long price;
    private final LocalDateTime saleStartAt;
}
