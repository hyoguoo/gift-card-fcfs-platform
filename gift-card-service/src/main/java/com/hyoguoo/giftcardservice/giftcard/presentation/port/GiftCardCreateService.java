package com.hyoguoo.giftcardservice.giftcard.presentation.port;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.CreateNewGiftCardCommand;

public interface GiftCardCreateService {

    void createNewGiftCard(CreateNewGiftCardCommand command);
}
