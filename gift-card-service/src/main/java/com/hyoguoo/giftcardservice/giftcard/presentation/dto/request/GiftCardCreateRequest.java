package com.hyoguoo.giftcardservice.giftcard.presentation.dto.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardCreateRequest {

    private final String giftCardName;
    private final Long quantity;
    private final Long price;
    private final Long totalBalance;
    private final Integer validityDays;
    private final LocalDateTime saleStartAt;
}
