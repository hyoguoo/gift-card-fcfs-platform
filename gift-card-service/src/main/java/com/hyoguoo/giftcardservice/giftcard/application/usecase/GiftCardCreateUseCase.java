package com.hyoguoo.giftcardservice.giftcard.application.usecase;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.CreateNewGiftCardCommand;
import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardRepository;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardCreateUseCase {

    private final GiftCardRepository giftCardRepository;

    public GiftCard createNewGiftCard(CreateNewGiftCardCommand command) {
        GiftCard giftCard = GiftCard.requiredArgsBuilder()
                .giftCardName(command.getGiftCardName())
                .quantity(command.getQuantity())
                .price(command.getPrice())
                .totalBalance(command.getTotalBalance())
                .validityDays(command.getValidityDays())
                .saleStartAt(command.getSaleStartAt())
                .requiredArgsBuild();

        return giftCardRepository.saveOrUpdate(giftCard);
    }
}
