package com.hyoguoo.paymentservice.payment.application.port;

public interface OrderedGiftCardStockMessageProducer {

    void sendGiftCardStockDecreaseEventMessage(Long giftCardId, Integer stockQuantity);

    void sendGiftCardStockRollbackEventMessage(Long giftCardId, Integer stockQuantity);
}
