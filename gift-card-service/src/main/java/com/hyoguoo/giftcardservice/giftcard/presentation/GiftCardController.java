package com.hyoguoo.giftcardservice.giftcard.presentation;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.CreateNewGiftCardCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardUseCommand;
import com.hyoguoo.giftcardservice.giftcard.presentation.dto.request.GiftCardCreateRequest;
import com.hyoguoo.giftcardservice.giftcard.presentation.dto.request.GiftCardUseRequest;
import com.hyoguoo.giftcardservice.giftcard.presentation.port.GiftCardCreateService;
import com.hyoguoo.giftcardservice.giftcard.presentation.port.GiftCardUseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GiftCardController {

    private final GiftCardCreateService giftCardCreateService;
    private final GiftCardUseService giftCardUseService;

    @PostMapping("/gift-card/create")
    public void createGiftCard(@RequestBody GiftCardCreateRequest request) {
        CreateNewGiftCardCommand command = CreateNewGiftCardCommand.builder()
                .giftCardName(request.getGiftCardName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .totalBalance(request.getTotalBalance())
                .validityDays(request.getValidityDays())
                .saleStartAt(request.getSaleStartAt())
                .build();

        giftCardCreateService.createNewGiftCard(command);
    }

    @PostMapping("/gift-card/use")
    public void useGiftCard(@RequestBody GiftCardUseRequest request, @RequestHeader("X-USER-ID") Long userId) {
        GiftCardUseCommand command = GiftCardUseCommand.builder()
                .giftCardUserId(request.getUserGiftCardId())
                .usedAmount(request.getUsedAmount())
                .usedUserId(userId)
                .build();

        giftCardUseService.useGiftCard(command);
    }
}
