package com.hyoguoo.orderservice.order.domain;

import com.hyoguoo.orderservice.order.application.dto.command.CheckoutCommand;
import com.hyoguoo.orderservice.order.domain.dto.GiftCardInfo;
import com.hyoguoo.orderservice.order.domain.enums.OrderStatus;
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
    protected OrderInfo(CheckoutCommand command, GiftCardInfo giftCardInfo, String orderId, LocalDateTime orderedAt) {
        this.buyerId = command.getBuyerId();
        this.giftCardId = giftCardInfo.getGiftCardId();
        this.paymentAmount = giftCardInfo.getPrice();
        this.orderId = orderId;
        this.orderedAt = orderedAt;
        this.orderName = "Order for gift card " + giftCardId;

        this.orderStatus = OrderStatus.PENDING;

        validateNewOrder(giftCardInfo);
    }

    private void validateNewOrder(GiftCardInfo giftCardInfo) {
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

        if (this.orderedAt.isBefore(giftCardInfo.getSaleStartAt())) {
            throw new IllegalArgumentException("Order cannot be placed before sale start at");
        }
    }
}
