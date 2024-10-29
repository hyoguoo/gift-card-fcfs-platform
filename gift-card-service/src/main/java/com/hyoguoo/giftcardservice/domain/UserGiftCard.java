package com.hyoguoo.giftcardservice.domain;

import com.hyoguoo.giftcardservice.domain.enums.UserGiftCardStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "allArgsBuilder", buildMethodName = "allArgsBuild")
public class UserGiftCard {

    private Long id;
    private Long giftCardId;
    private Long userId;
    private LocalDateTime purchaseDate;
    private UserGiftCardStatus userGiftCardStatus;

    @Builder(builderMethodName = "requiredArgsBuilder", buildMethodName = "requiredArgsBuild")
    public UserGiftCard(Long giftCardId, Long userId, LocalDateTime purchaseDate) {
        this.giftCardId = giftCardId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.userGiftCardStatus = UserGiftCardStatus.ACTIVE;

        validateNewUserGiftCard();
    }

    private void validateNewUserGiftCard() {
        if (this.giftCardId == null || this.giftCardId <= 0) {
            throw new IllegalArgumentException("Gift card ID is required");
        }

        if (this.userId == null || this.userId <= 0) {
            throw new IllegalArgumentException("User ID is required");
        }

        if (this.purchaseDate == null) {
            throw new IllegalArgumentException("Purchase date is required");
        }
    }
}
