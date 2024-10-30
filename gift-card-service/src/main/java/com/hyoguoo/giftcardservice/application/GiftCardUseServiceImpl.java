package com.hyoguoo.giftcardservice.application;

import com.hyoguoo.giftcardservice.application.dto.command.GiftCardUseCommand;
import com.hyoguoo.giftcardservice.application.usecase.GiftCardLoadUseCase;
import com.hyoguoo.giftcardservice.application.usecase.GiftCardUsageUseCase;
import com.hyoguoo.giftcardservice.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.presentation.port.GiftCardUseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardUseServiceImpl implements GiftCardUseService {

    private final GiftCardLoadUseCase giftCardLoadUseCase;
    private final GiftCardUsageUseCase giftCardUsageUseCase;

    public void useGiftCard(GiftCardUseCommand command) {
        GiftCardUser giftCardUser = giftCardLoadUseCase.loadGiftCard(command.getUserGiftCardId());

        giftCardUsageUseCase.useGiftCard(giftCardUser, command);
    }
}
