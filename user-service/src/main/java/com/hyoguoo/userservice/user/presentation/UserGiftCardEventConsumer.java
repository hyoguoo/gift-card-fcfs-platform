package com.hyoguoo.userservice.user.presentation;

import com.hyoguoo.kafka.message.GiftCardUserEventMessage;
import com.hyoguoo.kafka.message.enums.EventType;
import com.hyoguoo.kafka.message.vo.GiftCardUserEventData;
import com.hyoguoo.userservice.user.application.dto.command.UserGiftCardUpsertCommand;
import com.hyoguoo.userservice.user.domain.enums.UserGiftCardStatus;
import com.hyoguoo.userservice.user.presentation.port.UserGiftCardSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGiftCardEventConsumer {

    private final UserGiftCardSyncService userGiftCardSyncService;

    @KafkaListener(topics = "${kafka.topics.gift-card-user-sync}", groupId = "${kafka.groups.gift-card-user-sync}")
    public void consumeGiftCardUserSyncEvent(GiftCardUserEventMessage message) {
        log.info("Consuming {} user gift card id: {}", message.getEventType(), message.getData().getId());
        GiftCardUserEventData data = message.getData();
        UserGiftCardUpsertCommand command = UserGiftCardUpsertCommand.builder()
                .id(data.getId())
                .giftCardId(data.getGiftCardId())
                .userId(data.getUserId())
                .purchaseDate(data.getPurchaseDate())
                .expirationDate(data.getExpirationDate())
                .usedDate(data.getUsedDate())
                .remainingBalance(data.getRemainingBalance())
                .totalBalance(data.getTotalBalance())
                .userGiftCardStatus(UserGiftCardStatus.from(data.getUserGiftCardStatus().getStatus()))
                .build();

        if (message.getEventType() == EventType.UPSERT) {
            userGiftCardSyncService.upsertUserGiftCard(command);
        }
    }
}
