package com.hyoguoo.giftcardservice.presentation.port;

import com.hyoguoo.giftcardservice.application.dto.command.FindMyGiftCardCursorCommand;
import com.hyoguoo.giftcardservice.application.dto.result.MyGiftCardListResult;

public interface GiftCardFindService {

    MyGiftCardListResult findMyGiftCardList(FindMyGiftCardCursorCommand command);
}
