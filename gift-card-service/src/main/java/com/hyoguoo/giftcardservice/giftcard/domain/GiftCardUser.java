package com.hyoguoo.giftcardservice.giftcard.domain;

import com.hyoguoo.giftcardservice.giftcard.domain.enums.UserGiftCardStatus;
import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardUserUseValidationException;
import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardUserValidationException;
import com.hyoguoo.giftcardservice.giftcard.exception.common.GiftCardErrorCode;
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
            throw GiftCardUserValidationException.of(GiftCardErrorCode.GIFT_CARD_ID_REQUIRED);
        }

        if (this.userId == null || this.userId <= 0) {
            throw GiftCardUserValidationException.of(GiftCardErrorCode.USER_ID_REQUIRED);
        }

        if (this.purchaseDate == null) {
            throw GiftCardUserValidationException.of(GiftCardErrorCode.PURCHASE_DATE_REQUIRED);
        }

        if (this.remainingBalance == null || this.remainingBalance <= 0) {
            throw GiftCardUserValidationException.of(GiftCardErrorCode.REMAINING_BALANCE_INVALID);
        }

        if (this.totalBalance == null || this.totalBalance <= 0) {
            throw GiftCardUserValidationException.of(GiftCardErrorCode.TOTAL_BALANCE_INVALID);
        }

        if (validityDays == null || validityDays <= 0) {
            throw GiftCardUserValidationException.of(GiftCardErrorCode.VALIDITY_DAYS_INVALID);
        }
    }

    public void useBalance(Long amount, LocalDateTime now, Long usedUserId) {
        if (userGiftCardStatus == UserGiftCardStatus.USED ||
                userGiftCardStatus == UserGiftCardStatus.EXPIRED ||
                userGiftCardStatus == UserGiftCardStatus.CANCELED) {
            throw GiftCardUserUseValidationException.of(GiftCardErrorCode.USER_STATUS_INVALID);
        }

        if (now.isAfter(this.expirationDate)) {
            throw GiftCardUserUseValidationException.of(GiftCardErrorCode.EXPIRED_GIFT_CARD);
        }

        if (this.purchaseDate.isAfter(now)) {
            throw GiftCardUserUseValidationException.of(GiftCardErrorCode.PURCHASE_DATE_INVALID);
        }

        if (!this.userId.equals(usedUserId)) {
            throw GiftCardUserUseValidationException.of(GiftCardErrorCode.USER_ID_MISMATCH);
        }

        if (amount == null || amount <= 0) {
            throw GiftCardUserUseValidationException.of(GiftCardErrorCode.USAGE_AMOUNT_INVALID);
        }

        if (remainingBalance < amount) {
            throw GiftCardUserUseValidationException.of(GiftCardErrorCode.INSUFFICIENT_BALANCE);
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
