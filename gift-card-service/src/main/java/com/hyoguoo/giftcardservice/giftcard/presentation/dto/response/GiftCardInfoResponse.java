package com.hyoguoo.giftcardservice.giftcard.presentation.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardInfoResponse {

    private final Long giftCardId;
    private final String giftCardName;
    private final Long price;
    private final LocalDateTime saleStartAt;
}
