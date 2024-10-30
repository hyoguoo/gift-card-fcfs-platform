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

    @SuppressWarnings("unused")
    @Builder(builderMethodName = "requiredArgsBuilder", buildMethodName = "requiredArgsBuild")
    protected OrderInfo(Long buyerId, Long giftCardId, Long paymentAmount, String orderId, LocalDateTime orderedAt) {
        this.buyerId = buyerId;
        this.giftCardId = giftCardId;
        this.paymentAmount = paymentAmount;
        this.orderId = orderId;
        this.orderedAt = orderedAt;
        this.orderName = "Order for gift card " + giftCardId;

        this.orderStatus = OrderStatus.PENDING;

        validateNewOrder();
    }

    private void validateNewOrder() {
        if (this.buyerId == null || this.buyerId <= 0) {
            throw new IllegalArgumentException("Buyer ID is required");
        }

        if (this.giftCardId == null || this.giftCardId <= 0) {
            throw new IllegalArgumentException("Gift card ID is required");
        }

        if (this.paymentAmount == null || this.paymentAmount < 0) {
            throw new IllegalArgumentException("Payment amount must be greater than or equal to 0");
        }

        if (this.orderId == null || this.orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID is required");
        }

        if (this.orderedAt == null) {
            throw new IllegalArgumentException("Ordered at is required");
        }
    }
}
