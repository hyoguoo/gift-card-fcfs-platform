package com.hyoguoo.giftcardservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "allArgsBuilder", buildMethodName = "allArgsBuild")
public class GiftCard {

    private Long id;
    private String giftCardName;
    private Long quantity;
    private Long price;

    @Builder(builderMethodName = "requiredArgsBuilder", buildMethodName = "requiredArgsBuild")
    public GiftCard(String giftCardName, Long quantity, Long price) {
        this.giftCardName = giftCardName;
        this.quantity = quantity;
        this.price = price;

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
    }
}