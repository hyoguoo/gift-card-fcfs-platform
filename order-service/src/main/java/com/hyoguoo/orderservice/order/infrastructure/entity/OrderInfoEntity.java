package com.hyoguoo.orderservice.order.infrastructure.entity;

import com.hyoguoo.orderservice.core.common.infrastructure.BaseEntity;
import com.hyoguoo.orderservice.order.domain.OrderInfo;
import com.hyoguoo.orderservice.order.domain.enums.OrderStatus;
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
@Table(name = "order_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buyer_id", nullable = false)
    private Long buyerId;

    @Column(name = "gift_card_id", nullable = false)
    private Long giftCardId;

    @Column(name = "payment_amount", nullable = false)
    private Long paymentAmount;

    @Column(name = "order_name", nullable = false)
    private String orderName;

    @Column(name = "order_id", unique = true, nullable = false)
    private String orderId;

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime orderedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "cancel_reason")
    private String cancelReason;

    public static OrderInfoEntity from(OrderInfo orderInfo) {
        return OrderInfoEntity.builder()
                .id(orderInfo.getId())
                .buyerId(orderInfo.getBuyerId())
                .giftCardId(orderInfo.getGiftCardId())
                .paymentAmount(orderInfo.getPaymentAmount())
                .orderName(orderInfo.getOrderName())
                .orderId(orderInfo.getOrderId())
                .orderedAt(orderInfo.getOrderedAt())
                .orderStatus(orderInfo.getOrderStatus())
                .cancelReason(orderInfo.getCancelReason())
                .build();
    }

    public OrderInfo toDomain() {
        return OrderInfo.allArgsBuilder()
                .id(id)
                .buyerId(buyerId)
                .giftCardId(giftCardId)
                .paymentAmount(paymentAmount)
                .orderName(orderName)
                .orderId(orderId)
                .orderedAt(orderedAt)
                .orderStatus(orderStatus)
                .cancelReason(cancelReason)
                .allArgsBuild();
    }
}
