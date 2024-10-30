package com.hyoguoo.orderservice.application.port;

import com.hyoguoo.orderservice.domain.dto.GiftCardInfo;

public interface GiftCardPort {

    GiftCardInfo getGiftCardInfoById(Long giftCardId);
}
