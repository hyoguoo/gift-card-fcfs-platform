package com.hyoguoo.giftcardservice.giftcard.domain;

import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardValidationException;
import com.hyoguoo.giftcardservice.giftcard.exception.common.GiftCardErrorCode;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "allArgsBuilder", buildMethodName = "allArgsBuild")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GiftCard {

    private Long id;
    private String giftCardName;
    private Long quantity;
    private Long price;
    private Long totalBalance;
    private Integer validityDays;
    private LocalDateTime saleStartAt;

    @SuppressWarnings("unused")
    @Builder(builderMethodName = "requiredArgsBuilder", buildMethodName = "requiredArgsBuild")
    public GiftCard(String giftCardName, Long quantity, Long price, Long totalBalance, Integer validityDays, LocalDateTime saleStartAt) {
        this.giftCardName = giftCardName;
        this.quantity = quantity;
        this.price = price;
        this.totalBalance = totalBalance;
        this.validityDays = validityDays;
        this.saleStartAt = saleStartAt;

        validateNewGiftCard();
    }

    private void validateNewGiftCard() {
        if (this.giftCardName == null || this.giftCardName.isEmpty()) {
            throw GiftCardValidationException.of(GiftCardErrorCode.GIFT_CARD_NAME_REQUIRED);
        }

        if (this.quantity == null || this.quantity <= 0) {
            throw GiftCardValidationException.of(GiftCardErrorCode.QUANTITY_INVALID);
        }

        if (this.price == null || this.price <= 0) {
            throw GiftCardValidationException.of(GiftCardErrorCode.PRICE_INVALID);
        }

        if (this.totalBalance == null || this.totalBalance <= 0) {
            throw GiftCardValidationException.of(GiftCardErrorCode.TOTAL_BALANCE_INVALID);
        }

        if (this.validityDays == null || this.validityDays <= 0) {
            throw GiftCardValidationException.of(GiftCardErrorCode.VALIDITY_DAYS_INVALID);
        }

        if (this.saleStartAt == null) {
            throw GiftCardValidationException.of(GiftCardErrorCode.SALE_START_AT_REQUIRED);
        }
    }
}
