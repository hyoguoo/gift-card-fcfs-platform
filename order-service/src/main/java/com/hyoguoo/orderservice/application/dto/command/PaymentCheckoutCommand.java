package com.hyoguoo.orderservice.application.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentCheckoutCommand {

    private final Long buyerId;
    private final Long orderInfoId;
    private final String orderId;
}
