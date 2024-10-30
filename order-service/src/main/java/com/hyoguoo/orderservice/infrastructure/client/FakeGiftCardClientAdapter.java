package com.hyoguoo.orderservice.infrastructure.client;

import com.hyoguoo.orderservice.application.port.GiftCardPort;
import com.hyoguoo.orderservice.domain.dto.GiftCardInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FakeGiftCardClientAdapter implements GiftCardPort {

    @Override
    public GiftCardInfo getGiftCardInfoById(Long giftCardId) {
        return GiftCardInfo.builder()
                .giftCardId(giftCardId)
                .giftCardName("Fake Gift Card")
                .price(10000L)
                .build();
    }
}
