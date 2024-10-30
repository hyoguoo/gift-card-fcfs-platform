package com.hyoguoo.giftcardservice.presentation.port;

import com.hyoguoo.giftcardservice.application.dto.command.GiftCardUseCommand;

public interface GiftCardUseService {

    void useGiftCard(GiftCardUseCommand command);
}
