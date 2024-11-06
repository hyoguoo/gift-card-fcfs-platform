package com.hyoguoo.giftcardservice.giftcard.application.usecase;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardStockDecreaseCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardStockIncreaseCommand;
import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GiftCardStockUseCase {

    private final GiftCardRepository giftCardRepository;

    @Transactional
    public void decreaseQuantity(GiftCardStockDecreaseCommand command) {
        giftCardRepository.decreaseQuantity(command.getGiftCardId(), command.getStockQuantity());
    }

    @Transactional
    public void increaseQuantity(GiftCardStockIncreaseCommand command) {
        giftCardRepository.increaseQuantity(command.getGiftCardId(), command.getStockQuantity());
    }
}
