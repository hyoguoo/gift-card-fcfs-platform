package com.hyoguoo.kafka.message.vo;

import com.hyoguoo.kafka.message.vo.enums.UserGiftCardStatus;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GiftCardUserEventData {

    private Long id;
    private Long giftCardId;
    private Long userId;
    private LocalDateTime purchaseDate;
    private LocalDateTime expirationDate;
    private LocalDateTime usedDate;
    private Long remainingBalance;
    private Long totalBalance;
    private UserGiftCardStatus userGiftCardStatus;
}
