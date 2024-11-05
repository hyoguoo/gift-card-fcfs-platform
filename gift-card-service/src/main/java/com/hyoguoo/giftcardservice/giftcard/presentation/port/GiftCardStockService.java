package com.hyoguoo.giftcardservice.giftcard.presentation.port;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardStockDecreaseCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardStockIncreaseCommand;

public interface GiftCardStockService {

    void decreaseQuantity(GiftCardStockDecreaseCommand command);

    void increaseQuantity(GiftCardStockIncreaseCommand command);
}
