package com.hyoguoo.giftcardservice.giftcard.presentation;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardPurchaseCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardStockDecreaseCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardStockIncreaseCommand;
import com.hyoguoo.giftcardservice.giftcard.presentation.port.GiftCardPurchaseService;
import com.hyoguoo.giftcardservice.giftcard.presentation.port.GiftCardStockService;
import com.hyoguoo.kafka.message.GiftCardPurchaseEventMessage;
import com.hyoguoo.kafka.message.GiftCardStockDecreaseEventMessage;
import com.hyoguoo.kafka.message.GiftCardStockRollbackEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiftCardEventConsumer {

    private final GiftCardStockService giftCardStockService;
    private final GiftCardPurchaseService giftCardPurchaseService;

    @KafkaListener(topics = "${kafka.topics.gift-card-stock-decrease}", groupId = "${kafka.groups.gift-card-stock-decrease}")
    public void consumeStockDecreaseEvent(GiftCardStockDecreaseEventMessage message) {
        log.info("Consuming decrease: {} quantity: {}", message.getGiftCardId(), message.getStockQuantity());
        GiftCardStockDecreaseCommand command = GiftCardStockDecreaseCommand.builder()
                .giftCardId(message.getGiftCardId())
                .stockQuantity(message.getStockQuantity())
                .build();

        giftCardStockService.decreaseQuantity(command);
    }

    @KafkaListener(topics = "${kafka.topics.gift-card-stock-rollback}", groupId = "${kafka.groups.gift-card-stock-rollback}")
    public void consumeStockRollbackEvent(GiftCardStockRollbackEventMessage message) {
        log.info("Consuming rollback: {} quantity: {}", message.getGiftCardId(), message.getStockQuantity());
        GiftCardStockIncreaseCommand command = GiftCardStockIncreaseCommand.builder()
                .giftCardId(message.getGiftCardId())
                .stockQuantity(message.getStockQuantity())
                .build();

        giftCardStockService.increaseQuantity(command);
    }

    @KafkaListener(topics = "${kafka.topics.gift-card-purchase}", groupId = "${kafka.groups.gift-card-purchase}")
    public void consumeGiftCardPurchaseEvent(GiftCardPurchaseEventMessage message) {
        log.info("Consuming purchase: {} by user: {}", message.getGiftCardId(), message.getUserId());
        GiftCardPurchaseCommand command = GiftCardPurchaseCommand.builder()
                .giftCardId(message.getGiftCardId())
                .userId(message.getUserId())
                .build();

        giftCardPurchaseService.purchaseGiftCard(command);
    }
}
