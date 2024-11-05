package com.hyoguoo.giftcardservice.giftcard.presentation.port;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardPurchaseCommand;

public interface GiftCardPurchaseService {

    void purchaseGiftCard(GiftCardPurchaseCommand command);
}
