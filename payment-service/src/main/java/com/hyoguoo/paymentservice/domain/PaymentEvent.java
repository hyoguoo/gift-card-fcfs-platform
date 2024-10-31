package com.hyoguoo.paymentservice.domain;


import com.hyoguoo.paymentservice.domain.dto.OrderInfo;
import com.hyoguoo.paymentservice.domain.enums.PaymentEventStatus;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "allArgsBuilder", buildMethodName = "allArgsBuild")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentEvent {

    private Long id;
    private Long buyerId;
    private Long orderInfoId;
    private Long orderedGiftCardId;
    private String orderId;
    private String paymentKey;
    private Long totalAmount;
    private PaymentEventStatus status;
    private LocalDateTime executedAt;
    private LocalDateTime approvedAt;

    @Builder(builderMethodName = "requiredBuilder", buildMethodName = "requiredBuild")
    @SuppressWarnings("unused")
    protected PaymentEvent(
            OrderInfo orderInfo
    ) {
        this.buyerId = orderInfo.getBuyerId();
        this.orderInfoId = orderInfo.getOrderInfoId();
        this.orderedGiftCardId = orderInfo.getGiftCardId();
        this.orderId = orderInfo.getOrderId();
        this.totalAmount = orderInfo.getPaymentAmount();

        this.status = PaymentEventStatus.READY;
    }
}
