package com.hyoguoo.giftcardservice.giftcard.application;

import com.hyoguoo.kafka.message.GiftCardStockDecreaseEventMessage;
import com.hyoguoo.kafka.message.GiftCardStockRollbackEventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GiftCardEventConsumer {

    @KafkaListener(topics = "${kafka.topics.gift-card-stock-decrease}", groupId = "${kafka.groups.gift-card-stock-decrease}")
    public void consumeStockDecreaseEvent(GiftCardStockDecreaseEventMessage message) {
        System.out.println("Consuming decrease: " + message.getGiftCardId() + " quantity: " + message.getStockQuantity());
    }

    @KafkaListener(topics = "${kafka.topics.gift-card-stock-rollback}", groupId = "${kafka.groups.gift-card-stock-rollback}")
    public void consumeStockRollbackEvent(GiftCardStockRollbackEventMessage message) {
        System.out.println("Consuming rollback: " + message.getGiftCardId() + " quantity: " + message.getStockQuantity());
    }
}
