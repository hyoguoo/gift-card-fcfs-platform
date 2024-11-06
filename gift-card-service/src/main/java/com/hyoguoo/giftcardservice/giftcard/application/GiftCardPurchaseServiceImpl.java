package com.hyoguoo.giftcardservice.giftcard.application;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardPurchaseCommand;
import com.hyoguoo.giftcardservice.giftcard.application.usecase.GiftCardLoadUseCase;
import com.hyoguoo.giftcardservice.giftcard.application.usecase.GiftCardUserCreateUseCase;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCard;
import com.hyoguoo.giftcardservice.giftcard.presentation.port.GiftCardPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardPurchaseServiceImpl implements GiftCardPurchaseService {

    private final GiftCardLoadUseCase giftCardLoadUseCase;
    private final GiftCardUserCreateUseCase giftCardUserCreateUseCase;

    @Override
    public void purchaseGiftCard(GiftCardPurchaseCommand command) {
        GiftCard giftCard = giftCardLoadUseCase.getGiftCardById(command.getGiftCardId());
        giftCardUserCreateUseCase.createNewGiftCardUser(giftCard, command.getUserId());
    }
}
