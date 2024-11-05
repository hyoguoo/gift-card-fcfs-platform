package com.hyoguoo.giftcardservice.giftcard.presentation.port;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardUseCommand;

public interface GiftCardUseService {

    void useGiftCard(GiftCardUseCommand command);
}
