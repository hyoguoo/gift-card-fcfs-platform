package com.hyoguoo.orderservice.order.application.dto.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckoutCommand {

    private final Long buyerId;
    private final Long giftCardId;
}
