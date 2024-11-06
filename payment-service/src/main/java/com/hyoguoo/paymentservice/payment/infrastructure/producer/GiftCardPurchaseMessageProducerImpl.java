package com.hyoguoo.paymentservice.payment.infrastructure.producer;

import com.hyoguoo.kafka.message.GiftCardPurchaseEventMessage;
import com.hyoguoo.paymentservice.payment.application.port.GiftCardPurchaseMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GiftCardPurchaseMessageProducerImpl implements GiftCardPurchaseMessageProducer {

    private final KafkaTemplate<String, GiftCardPurchaseEventMessage> giftCardPurchaseKafkaTemplate;

    @Override
    public void sendGiftCardPurchaseEventMessage(Long giftCardId, Long userId) {
        GiftCardPurchaseEventMessage message = GiftCardPurchaseEventMessage.builder()
                .giftCardId(giftCardId)
                .userId(userId)
                .build();

        giftCardPurchaseKafkaTemplate.sendDefault(message);
    }
}
