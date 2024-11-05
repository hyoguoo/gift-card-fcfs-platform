package com.hyoguoo.paymentservice.payment.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentCheckoutRequest {

    private final Long buyerId;
    private final Long orderInfoId;
    private final String orderId;
    private final Long giftCardId;
    private final Long giftCardPrice;
}
