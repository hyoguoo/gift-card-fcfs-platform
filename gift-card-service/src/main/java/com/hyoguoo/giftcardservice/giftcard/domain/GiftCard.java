package com.hyoguoo.giftcardservice.giftcard.domain;

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
            throw new IllegalArgumentException("Gift card name is required");
        }

        if (this.quantity == null || this.quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (this.price == null || this.price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (this.totalBalance == null || this.totalBalance <= 0) {
            throw new IllegalArgumentException("Total balance must be greater than 0");
        }

        if (this.validityDays == null || this.validityDays <= 0) {
            throw new IllegalArgumentException("Validity days must be greater than 0");
        }

        if (this.saleStartAt == null) {
            throw new IllegalArgumentException("Sale start at is required");
        }
    }
}
