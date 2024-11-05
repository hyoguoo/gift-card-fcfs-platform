package com.hyoguoo.giftcardservice.giftcard.application;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardStockDecreaseCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardStockIncreaseCommand;
import com.hyoguoo.giftcardservice.giftcard.application.usecase.GiftCardStockUseCase;
import com.hyoguoo.giftcardservice.giftcard.presentation.port.GiftCardStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardStockServiceImpl implements GiftCardStockService {

    private final GiftCardStockUseCase giftCardStockUseCase;

    @Override
    public void decreaseQuantity(GiftCardStockDecreaseCommand command) {
        giftCardStockUseCase.decreaseQuantity(command);
    }

    @Override
    public void increaseQuantity(GiftCardStockIncreaseCommand command) {
        giftCardStockUseCase.increaseQuantity(command);
    }
}
