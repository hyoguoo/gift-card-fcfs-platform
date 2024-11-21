package com.hyoguoo.orderservice.order.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckoutResponse {

    private final String orderId;
    private final Long totalAmount;
}
