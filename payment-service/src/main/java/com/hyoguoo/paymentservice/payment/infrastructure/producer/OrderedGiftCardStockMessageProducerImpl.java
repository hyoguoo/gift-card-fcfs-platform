package com.hyoguoo.paymentservice.payment.infrastructure.producer;

import com.hyoguoo.kafka.message.GiftCardStockDecreaseEventMessage;
import com.hyoguoo.kafka.message.GiftCardStockRollbackEventMessage;
import com.hyoguoo.paymentservice.payment.application.port.OrderedGiftCardStockMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderedGiftCardStockMessageProducerImpl implements OrderedGiftCardStockMessageProducer {

    private final KafkaTemplate<String, GiftCardStockDecreaseEventMessage> stockDecreaseKafkaTemplate;
    private final KafkaTemplate<String, GiftCardStockRollbackEventMessage> rollbackStockKafkaTemplate;

    @Override
    public void sendGiftCardStockDecreaseEventMessage(Long giftCardId, Integer stockQuantity) {
        GiftCardStockDecreaseEventMessage message = GiftCardStockDecreaseEventMessage.builder()
                .giftCardId(giftCardId)
                .stockQuantity(stockQuantity)
                .build();
        stockDecreaseKafkaTemplate.sendDefault(message);
    }

    @Override
    public void sendGiftCardStockRollbackEventMessage(Long giftCardId, Integer stockQuantity) {
        GiftCardStockRollbackEventMessage message = GiftCardStockRollbackEventMessage.builder()
                .giftCardId(giftCardId)
                .stockQuantity(stockQuantity)
                .build();
        rollbackStockKafkaTemplate.sendDefault(message);
    }
}
