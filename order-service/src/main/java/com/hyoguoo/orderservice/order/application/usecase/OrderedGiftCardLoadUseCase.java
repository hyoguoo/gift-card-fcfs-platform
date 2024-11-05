package com.hyoguoo.orderservice.order.application.usecase;

import com.hyoguoo.orderservice.order.application.port.GiftCardPort;
import com.hyoguoo.orderservice.order.domain.dto.GiftCardInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderedGiftCardLoadUseCase {

    private final GiftCardPort giftCardPort;

    public GiftCardInfo loadOrderedGiftCard(Long giftCardId) {
        return giftCardPort.getGiftCardInfoById(giftCardId);
    }
}
