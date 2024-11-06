package com.hyoguoo.paymentservice.payment.application.port;

public interface GiftCardPurchaseMessageProducer {

    void sendGiftCardPurchaseEventMessage(Long giftCardId, Long userId);
}
