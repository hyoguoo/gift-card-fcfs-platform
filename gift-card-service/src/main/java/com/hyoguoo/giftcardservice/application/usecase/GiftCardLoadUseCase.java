package com.hyoguoo.giftcardservice.application.usecase;

import com.hyoguoo.giftcardservice.application.dto.command.FindMyGiftCardCursorCommand;
import com.hyoguoo.giftcardservice.domain.record.GiftCardUserRecord;
import com.hyoguoo.giftcardservice.infrastructure.repository.GiftCardUserRepository;
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
}
