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

    @SuppressWarnings("unused")
    @Builder(builderMethodName = "requiredArgsBuilder", buildMethodName = "requiredArgsBuild")
    public UserGiftCard(Long giftCardId,
            Long userId,
            LocalDateTime purchaseDate,
            Long totalBalance,
            Integer validityDays) {
        this.giftCardId = giftCardId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.userGiftCardStatus = UserGiftCardStatus.ACTIVE;
        this.remainingBalance = totalBalance;
        this.totalBalance = totalBalance;
        this.expirationDate = purchaseDate.plusDays(validityDays);

        validateNewUserGiftCard(validityDays);
    }

    private void validateNewUserGiftCard(Integer validityDays) {
        if (this.giftCardId == null || this.giftCardId <= 0) {
            throw new IllegalArgumentException("Gift card ID is required");
        }

        if (this.userId == null || this.userId <= 0) {
            throw new IllegalArgumentException("User ID is required");
        }

        if (this.purchaseDate == null) {
            throw new IllegalArgumentException("Purchase date is required");
        }

        if (this.remainingBalance == null || this.remainingBalance <= 0) {
            throw new IllegalArgumentException("Remaining balance must be greater than 0");
        }

        if (this.totalBalance == null || this.totalBalance <= 0) {
            throw new IllegalArgumentException("Total balance must be greater than 0");
        }

        if (validityDays == null || validityDays <= 0) {
            throw new IllegalArgumentException("Validity days must be greater than 0");
        }
    }
}
