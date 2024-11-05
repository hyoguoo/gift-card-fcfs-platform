package com.hyoguoo.giftcardservice.giftcard.application.usecase;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.FindMyGiftCardCursorCommand;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.giftcard.domain.record.GiftCardUserRecord;
import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardLoadUseCase {

    private final GiftCardUserRepository giftCardUserRepository;

    public Slice<GiftCardUserRecord> findSliceMyGiftCardWithCursor(FindMyGiftCardCursorCommand command) {
        return giftCardUserRepository.findSliceByUserId(command.getUserId(),
                command.getCursor(),
                command.getSize());
    }

    public GiftCardUser loadGiftCard(Long userGiftCardId) {
        return giftCardUserRepository.findById(userGiftCardId)
                .orElseThrow(() -> new IllegalArgumentException("GiftCardUser not found"));
    }
}
