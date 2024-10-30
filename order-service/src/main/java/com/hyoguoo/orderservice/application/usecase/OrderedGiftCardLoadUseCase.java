package com.hyoguoo.orderservice.application.usecase;

import com.hyoguoo.orderservice.application.port.GiftCardPort;
import com.hyoguoo.orderservice.domain.dto.GiftCardInfo;
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
