package com.hyoguoo.giftcardservice.giftcard.application.port;

import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;

public interface GiftCardUserSyncMessageProducer {

    void sendGiftCardUserUpsertEventMessage(GiftCardUser giftCardUser);
}
