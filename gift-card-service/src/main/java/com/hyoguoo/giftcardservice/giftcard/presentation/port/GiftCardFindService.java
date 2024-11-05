package com.hyoguoo.giftcardservice.giftcard.presentation.port;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.FindMyGiftCardCursorCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.result.GiftCardInfoResult;
import com.hyoguoo.giftcardservice.giftcard.application.dto.result.MyGiftCardListResult;

public interface GiftCardFindService {

    GiftCardInfoResult getGiftCardInfo(Long giftCardId);

    MyGiftCardListResult findMyGiftCardList(FindMyGiftCardCursorCommand command);
}
