package com.hyoguoo.orderservice.domain;

import com.hyoguoo.orderservice.domain.enums.OrderStatus;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "allArgsBuilder", buildMethodName = "allArgsBuild")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderInfo {

    private Long id;
    private Long buyerId;
    private Long giftCardId;
    private Long paymentAmount;
    private String orderName;
    private String orderId;
    private LocalDateTime orderedAt;
    private OrderStatus orderStatus;
    private String cancelReason;
}
