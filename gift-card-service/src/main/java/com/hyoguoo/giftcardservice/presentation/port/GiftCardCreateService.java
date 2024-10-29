package com.hyoguoo.giftcardservice.presentation.port;

import com.hyoguoo.giftcardservice.application.dto.command.CreateNewGiftCardCommand;

public interface GiftCardCreateService {

    void createNewGiftCard(CreateNewGiftCardCommand command);
}
