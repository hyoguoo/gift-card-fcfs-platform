package com.hyoguoo.userservice.user.domain;

import com.hyoguoo.userservice.user.domain.enums.UserGiftCardStatus;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "allArgsBuilder", buildMethodName = "allArgsBuild")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserGiftCard {

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
