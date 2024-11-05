package com.hyoguoo.orderservice.order.infrastructure.client;

import com.hyoguoo.orderservice.order.application.port.GiftCardPort;
import com.hyoguoo.orderservice.order.domain.dto.GiftCardInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GiftCardClientAdapter implements GiftCardPort {

    private final GiftCardFeignClient giftCardFeignClient;

    @Override
    public GiftCardInfo getGiftCardInfoById(Long giftCardId) {
        return giftCardFeignClient.getGiftCardInfo(giftCardId);
    }
}
