package com.hyoguoo.orderservice.order.domain;

import com.hyoguoo.orderservice.order.application.dto.command.CheckoutCommand;
import com.hyoguoo.orderservice.order.domain.dto.GiftCardInfo;
import com.hyoguoo.orderservice.order.domain.enums.OrderStatus;
import com.hyoguoo.orderservice.order.exception.OrderCompleteValidationException;
import com.hyoguoo.orderservice.order.exception.OrderFailValidationException;
import com.hyoguoo.orderservice.order.exception.OrderValidationException;
import com.hyoguoo.orderservice.order.exception.common.OrderErrorCode;
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
            throw OrderValidationException.of(OrderErrorCode.BUYER_ID_REQUIRED);
        }

        if (this.giftCardId == null || this.giftCardId <= 0) {
            throw OrderValidationException.of(OrderErrorCode.GIFT_CARD_ID_REQUIRED);
        }

        if (this.paymentAmount == null || this.paymentAmount < 0) {
            throw OrderValidationException.of(OrderErrorCode.PAYMENT_AMOUNT_INVALID);
        }

        if (this.orderId == null || this.orderId.isEmpty()) {
            throw OrderValidationException.of(OrderErrorCode.ORDER_ID_REQUIRED);
        }

        if (this.orderedAt == null) {
            throw OrderValidationException.of(OrderErrorCode.ORDERED_AT_REQUIRED);
        }

        if (this.orderedAt.isBefore(giftCardInfo.getSaleStartAt())) {
            throw OrderValidationException.of(OrderErrorCode.ORDER_BEFORE_SALE_START);
        }
    }

    public void completeOrder() {
        if (this.orderStatus != OrderStatus.PENDING &&
                this.orderStatus != OrderStatus.COMPLETED) {
            throw OrderCompleteValidationException.of(OrderErrorCode.ORDER_STATUS_PENDING_REQUIRED);
        }
        this.orderStatus = OrderStatus.COMPLETED;
    }

    public void failOrder() {
        if (this.orderStatus != OrderStatus.PENDING &&
                this.orderStatus != OrderStatus.FAILED) {
            throw OrderFailValidationException.of(OrderErrorCode.ORDER_STATUS_PENDING_REQUIRED);
        }
        this.orderStatus = OrderStatus.FAILED;
    }
}
