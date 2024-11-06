package com.hyoguoo.giftcardservice.giftcard.application;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.GiftCardUseCommand;
import com.hyoguoo.giftcardservice.giftcard.application.usecase.GiftCardLoadUseCase;
import com.hyoguoo.giftcardservice.giftcard.application.usecase.GiftCardUsageUseCase;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.giftcard.presentation.port.GiftCardUseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardUseServiceImpl implements GiftCardUseService {

    private final GiftCardLoadUseCase giftCardLoadUseCase;
    private final GiftCardUsageUseCase giftCardUsageUseCase;

    public void useGiftCard(GiftCardUseCommand command) {
        GiftCardUser giftCardUser = giftCardLoadUseCase.getGiftCardUserById(command.getGiftCardUserId());

        giftCardUsageUseCase.useGiftCard(giftCardUser, command);
    }
}
