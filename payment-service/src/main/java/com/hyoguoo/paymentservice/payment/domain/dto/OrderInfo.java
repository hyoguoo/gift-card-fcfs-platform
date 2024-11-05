package com.hyoguoo.paymentservice.payment.domain.dto;

import com.hyoguoo.paymentservice.payment.domain.dto.enums.OrderStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderInfo {

    private final Long orderInfoId;
    private final Long buyerId;
    private final Long giftCardId;
    private final Long paymentAmount;
    private final String orderId;
    private final LocalDateTime orderedAt;
    private final OrderStatus orderStatus;
}
