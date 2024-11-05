package com.hyoguoo.giftcardservice.giftcard.presentation;

import com.hyoguoo.giftcardservice.giftcard.application.dto.result.GiftCardInfoResult;
import com.hyoguoo.giftcardservice.giftcard.presentation.dto.response.GiftCardInfoResponse;
import com.hyoguoo.giftcardservice.giftcard.presentation.port.GiftCardFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InternalGiftCardController {

    private final GiftCardFindService giftCardFindService;

    @GetMapping("/gift-card/internal/{id}")
    public GiftCardInfoResponse getGiftCardInfo(@PathVariable Long id) {
        GiftCardInfoResult giftCardInfo = giftCardFindService.getGiftCardInfo(id);

        return GiftCardInfoResponse.builder()
                .giftCardId(giftCardInfo.getGiftCardId())
                .giftCardName(giftCardInfo.getGiftCardName())
                .price(giftCardInfo.getPrice())
                .saleStartAt(giftCardInfo.getSaleStartAt())
                .build();
    }
}
