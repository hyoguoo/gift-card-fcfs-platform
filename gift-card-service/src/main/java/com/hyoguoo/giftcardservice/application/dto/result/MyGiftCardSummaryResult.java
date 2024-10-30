package com.hyoguoo.giftcardservice.application.dto.result;

import com.hyoguoo.giftcardservice.domain.enums.UserGiftCardStatus;
import com.hyoguoo.giftcardservice.domain.record.GiftCardUserRecord;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyGiftCardSummaryResult {

    private final Long userGiftCardId;
    private final Long giftCardId;
    private final String giftCardName;
    private final UserGiftCardStatus userGiftCardStatus;
    private final LocalDateTime purchaseDate;

    public static MyGiftCardSummaryResult from(GiftCardUserRecord giftCardUserRecord) {
        return MyGiftCardSummaryResult.builder()
                .userGiftCardId(giftCardUserRecord.getUserGiftCardId())
                .giftCardId(giftCardUserRecord.getGiftCardId())
                .giftCardName(giftCardUserRecord.getGiftCardName())
                .userGiftCardStatus(giftCardUserRecord.getUserGiftCardStatus())
                .purchaseDate(giftCardUserRecord.getPurchaseDate())
                .build();
    }
}
