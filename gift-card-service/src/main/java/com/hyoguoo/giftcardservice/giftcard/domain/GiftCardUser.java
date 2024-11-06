package com.hyoguoo.giftcardservice.giftcard.domain;

import com.hyoguoo.giftcardservice.giftcard.domain.enums.UserGiftCardStatus;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "allArgsBuilder", buildMethodName = "allArgsBuild")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GiftCardUser {

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
    public GiftCardUser(GiftCard giftCard, Long userId, LocalDateTime purchaseDate) {
        this.giftCardId = giftCard.getId();
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.remainingBalance = giftCard.getTotalBalance();
        this.totalBalance = giftCard.getTotalBalance();
        this.expirationDate = purchaseDate.plusDays(giftCard.getValidityDays());

        this.userGiftCardStatus = UserGiftCardStatus.ACTIVE;

        validateNewUserGiftCard(giftCard.getValidityDays());
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

    // 낙관적 락 사용
    public void useBalance(Long amount, LocalDateTime now, Long usedUserId) {
        if (userGiftCardStatus == UserGiftCardStatus.USED ||
                userGiftCardStatus == UserGiftCardStatus.EXPIRED ||
                userGiftCardStatus == UserGiftCardStatus.CANCELED) {
            throw new IllegalStateException("Cannot use balance in current status: " + userGiftCardStatus);
        }

        if (now.isAfter(this.expirationDate)) {
            throw new IllegalStateException("Gift card has expired and cannot be used");
        }

        if (this.purchaseDate.isAfter(now)) {
            throw new IllegalStateException("Gift card has not been purchased yet");
        }

        if (!this.userId.equals(usedUserId)) {
            throw new IllegalArgumentException("User ID does not match");
        }

        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Usage amount must be greater than 0");
        }

        if (remainingBalance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        this.remainingBalance -= amount;

        if (this.remainingBalance == 0) {
            this.userGiftCardStatus = UserGiftCardStatus.USED;
            this.usedDate = now;
        } else if (this.remainingBalance < totalBalance) {
            this.userGiftCardStatus = UserGiftCardStatus.USING;
        }
    }
}
