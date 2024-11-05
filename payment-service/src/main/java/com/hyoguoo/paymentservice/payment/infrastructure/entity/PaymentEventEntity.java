package com.hyoguoo.paymentservice.payment.infrastructure.entity;

import com.hyoguoo.paymentservice.core.common.infrastructure.BaseEntity;
import com.hyoguoo.paymentservice.payment.domain.PaymentEvent;
import com.hyoguoo.paymentservice.payment.domain.enums.PaymentEventStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Builder
@Table(name = "payment_event")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentEventEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buyer_id", nullable = false)
    private Long buyerId;

    @Column(name = "order_info_id", nullable = false)
    private Long orderInfoId;

    @Column(name = "ordered_gift_card_id", nullable = false)
    private Long orderedGiftCardId;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "payment_key", nullable = false)
    private String paymentKey;

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentEventStatus status;

    @Column(name = "executed_at")
    private LocalDateTime executedAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    public static PaymentEventEntity from(PaymentEvent paymentEvent) {
        return PaymentEventEntity.builder()
                .id(paymentEvent.getId())
                .buyerId(paymentEvent.getBuyerId())
                .orderInfoId(paymentEvent.getOrderInfoId())
                .orderedGiftCardId(paymentEvent.getOrderedGiftCardId())
                .orderId(paymentEvent.getOrderId())
                .paymentKey(paymentEvent.getPaymentKey())
                .totalAmount(paymentEvent.getTotalAmount())
                .status(paymentEvent.getStatus())
                .executedAt(paymentEvent.getExecutedAt())
                .approvedAt(paymentEvent.getApprovedAt())
                .build();
    }

    public PaymentEvent toDomain() {
        return PaymentEvent.allArgsBuilder()
                .id(id)
                .buyerId(buyerId)
                .orderInfoId(orderInfoId)
                .orderedGiftCardId(orderedGiftCardId)
                .orderId(orderId)
                .paymentKey(paymentKey)
                .totalAmount(totalAmount)
                .status(status)
                .executedAt(executedAt)
                .approvedAt(approvedAt)
                .allArgsBuild();
    }
}
