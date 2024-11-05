package com.hyoguoo.orderservice.order.application.dto.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckoutResult {

    private final String orderId;
    private final Long totalAmount;
}
