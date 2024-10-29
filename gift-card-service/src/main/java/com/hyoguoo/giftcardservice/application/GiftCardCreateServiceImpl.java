package com.hyoguoo.giftcardservice.application;

import com.hyoguoo.giftcardservice.application.dto.command.CreateNewGiftCardCommand;
import com.hyoguoo.giftcardservice.application.usecase.GiftCardCreateUseCase;
import com.hyoguoo.giftcardservice.presentation.port.GiftCardCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardCreateServiceImpl implements GiftCardCreateService {

    private final GiftCardCreateUseCase giftCardCreateUseCase;

    @Override
    public void createNewGiftCard(CreateNewGiftCardCommand command) {
        giftCardCreateUseCase.createNewGiftCard(command);
    }
}
