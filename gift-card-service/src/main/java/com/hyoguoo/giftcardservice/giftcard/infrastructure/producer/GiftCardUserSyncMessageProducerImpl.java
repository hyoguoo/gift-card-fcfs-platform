package com.hyoguoo.giftcardservice.giftcard.infrastructure.producer;

import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardUserSyncMessageProducer;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;
import com.hyoguoo.kafka.message.GiftCardUserEventMessage;
import com.hyoguoo.kafka.message.enums.EventType;
import com.hyoguoo.kafka.message.vo.GiftCardUserEventData;
import com.hyoguoo.kafka.message.vo.enums.UserGiftCardStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GiftCardUserSyncMessageProducerImpl implements GiftCardUserSyncMessageProducer {

    private final KafkaTemplate<String, GiftCardUserEventMessage> giftCardUserDataSyncKafkaTemplate;

    @Override
    public void sendGiftCardUserUpsertEventMessage(GiftCardUser giftCardUser) {
        GiftCardUserEventMessage message = GiftCardUserEventMessage.builder()
                .eventType(EventType.UPSERT)
                .data(GiftCardUserEventData.builder()
                        .id(giftCardUser.getId())
                        .giftCardId(giftCardUser.getGiftCardId())
                        .userId(giftCardUser.getUserId())
                        .purchaseDate(giftCardUser.getPurchaseDate())
                        .expirationDate(giftCardUser.getExpirationDate())
                        .usedDate(giftCardUser.getUsedDate())
                        .remainingBalance(giftCardUser.getRemainingBalance())
                        .totalBalance(giftCardUser.getTotalBalance())
                        .userGiftCardStatus(UserGiftCardStatus.of(giftCardUser.getUserGiftCardStatus().getStatus()))
                        .build())
                .build();

        giftCardUserDataSyncKafkaTemplate.sendDefault(message);
    }
}
