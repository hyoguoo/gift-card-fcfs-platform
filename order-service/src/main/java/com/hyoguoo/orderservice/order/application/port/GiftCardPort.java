package com.hyoguoo.orderservice.order.application.port;

import com.hyoguoo.orderservice.order.domain.dto.GiftCardInfo;

public interface GiftCardPort {

    GiftCardInfo getGiftCardInfoById(Long giftCardId);
}
