package com.hyoguoo.giftcardservice.application.usecase;

import com.hyoguoo.giftcardservice.application.dto.command.CreateNewGiftCardCommand;
import com.hyoguoo.giftcardservice.application.port.GiftCardRepository;
import com.hyoguoo.giftcardservice.domain.GiftCard;
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
