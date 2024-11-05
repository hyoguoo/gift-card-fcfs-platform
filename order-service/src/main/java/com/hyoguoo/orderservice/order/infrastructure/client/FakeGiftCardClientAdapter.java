package com.hyoguoo.orderservice.order.infrastructure.client;

import com.hyoguoo.orderservice.order.application.port.GiftCardPort;
import com.hyoguoo.orderservice.order.domain.dto.GiftCardInfo;
import java.time.LocalDateTime;
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
                .saleStartAt(LocalDateTime.now().minusHours(1))
                .build();
    }
}
