package com.hyoguoo.giftcardservice.application.usecase;

import com.hyoguoo.giftcardservice.application.dto.command.GiftCardUseCommand;
import com.hyoguoo.giftcardservice.common.application.port.LocalDateTimeProvider;
import com.hyoguoo.giftcardservice.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.application.port.GiftCardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GiftCardUsageUseCase {

    private final LocalDateTimeProvider localDateTimeProvider;
    private final GiftCardUserRepository giftCardUserRepository;

    @Transactional
    public void useGiftCard(GiftCardUser giftCardUser, GiftCardUseCommand command) {
        giftCardUser.useBalance(command.getUsedAmount(), localDateTimeProvider.now(), command.getUsedUserId());
        giftCardUserRepository.saveOrUpdate(giftCardUser);
    }
}
