package com.hyoguoo.giftcardservice.application.dto.result;

import com.hyoguoo.giftcardservice.domain.enums.UserGiftCardStatus;
import com.hyoguoo.giftcardservice.domain.record.UserGiftCardRecord;
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

    public static MyGiftCardSummaryResult from(UserGiftCardRecord userGiftCardRecord) {
        return MyGiftCardSummaryResult.builder()
                .userGiftCardId(userGiftCardRecord.getUserGiftCardId())
                .giftCardId(userGiftCardRecord.getGiftCardId())
                .giftCardName(userGiftCardRecord.getGiftCardName())
                .userGiftCardStatus(userGiftCardRecord.getUserGiftCardStatus())
                .purchaseDate(userGiftCardRecord.getPurchaseDate())
                .build();
    }
}
