package com.hyoguoo.giftcardservice.giftcard.application.usecase;

import com.hyoguoo.giftcardservice.core.common.application.port.LocalDateTimeProvider;
import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardUserRepository;
import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardUserSyncMessageProducer;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCard;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardUserCreateUseCase {

    private final GiftCardUserRepository giftCardUserRepository;
    private final LocalDateTimeProvider localDateTimeProvider;
    private final GiftCardUserSyncMessageProducer giftCardUserSyncMessageProducer;

    public void createNewGiftCardUser(GiftCard giftCard, Long userId) {
        GiftCardUser giftCardUser = GiftCardUser.requiredArgsBuilder()
                .giftCard(giftCard)
                .userId(userId)
                .purchaseDate(localDateTimeProvider.now())
                .requiredArgsBuild();

        GiftCardUser savedGiftCardUser = giftCardUserRepository.saveOrUpdate(giftCardUser);

        giftCardUserSyncMessageProducer.sendGiftCardUserUpsertEventMessage(savedGiftCardUser);
    }
}
