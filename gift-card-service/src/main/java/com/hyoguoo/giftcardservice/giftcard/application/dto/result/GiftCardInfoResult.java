package com.hyoguoo.giftcardservice.giftcard.application.dto.result;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardInfoResult {

    private final Long giftCardId;
    private final String giftCardName;
    private final Long price;
    private final LocalDateTime saleStartAt;
}
