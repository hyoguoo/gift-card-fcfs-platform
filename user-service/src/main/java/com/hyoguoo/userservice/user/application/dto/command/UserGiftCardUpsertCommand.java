package com.hyoguoo.userservice.user.application.dto.command;

import com.hyoguoo.userservice.user.domain.enums.UserGiftCardStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserGiftCardUpsertCommand {

    private final Long id;
    private final Long giftCardId;
    private final Long userId;
    private final LocalDateTime purchaseDate;
    private final LocalDateTime expirationDate;
    private final LocalDateTime usedDate;
    private final Long remainingBalance;
    private final Long totalBalance;
    private final UserGiftCardStatus userGiftCardStatus;
}
