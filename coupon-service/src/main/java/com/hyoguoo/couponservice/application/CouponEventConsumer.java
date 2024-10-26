package com.hyoguoo.couponservice.application;

import com.hyoguoo.kafka.message.CouponStockDecreaseEventMessage;
import com.hyoguoo.kafka.message.CouponStockRollbackEventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponEventConsumer {

    @KafkaListener(topics = "${kafka.topics.coupon-stock-decrease}", groupId = "${kafka.groups.coupon-stock-decrease}")
    public void consumeStockDecreaseEvent(CouponStockDecreaseEventMessage message) {
        System.out.println("Consuming decrease: " + message.getCouponId() + " quantity: " + message.getStockQuantity());
    }

    @KafkaListener(topics = "${kafka.topics.coupon-stock-rollback}", groupId = "${kafka.groups.coupon-stock-rollback}")
    public void consumeStockRollbackEvent(CouponStockRollbackEventMessage message) {
        System.out.println("Consuming rollback: " + message.getCouponId() + " quantity: " + message.getStockQuantity());
    }
}
