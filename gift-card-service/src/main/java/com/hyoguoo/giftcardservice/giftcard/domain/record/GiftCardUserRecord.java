package com.hyoguoo.giftcardservice.giftcard.domain.record;

import com.hyoguoo.giftcardservice.giftcard.domain.enums.UserGiftCardStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GiftCardUserRecord {

    private final Long userGiftCardId;
    private final Long giftCardId;
    private final String giftCardName;
    private final UserGiftCardStatus userGiftCardStatus;
    private final LocalDateTime purchaseDate;
}
