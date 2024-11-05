package com.hyoguoo.orderservice.order.infrastructure.client;

import com.hyoguoo.orderservice.order.domain.dto.GiftCardInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gift-card-service")
public interface GiftCardFeignClient {

    @GetMapping("/gift-card/internal/{giftCardId}")
    GiftCardInfo getGiftCardInfo(@PathVariable Long giftCardId);
}
