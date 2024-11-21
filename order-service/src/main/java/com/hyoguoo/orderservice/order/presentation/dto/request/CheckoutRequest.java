package com.hyoguoo.orderservice.order.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckoutRequest {

    private final Long giftCardId;

    @JsonCreator
    public CheckoutRequest(Long giftCardId) {
        this.giftCardId = giftCardId;
    }
}
