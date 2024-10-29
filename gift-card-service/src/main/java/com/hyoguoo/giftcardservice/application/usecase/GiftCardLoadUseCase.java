package com.hyoguoo.giftcardservice.application.usecase;

import com.hyoguoo.giftcardservice.application.dto.command.FindMyGiftCardCursorCommand;
import com.hyoguoo.giftcardservice.domain.record.UserGiftCardRecord;
import com.hyoguoo.giftcardservice.infrastructure.repository.UserGiftCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardLoadUseCase {

    private final UserGiftCardRepository userGiftCardRepository;

    public Slice<UserGiftCardRecord> findSliceMyGiftCardWithCursor(FindMyGiftCardCursorCommand command) {
        return userGiftCardRepository.findSliceByUserId(command.getUserId(),
                command.getCursor(),
                command.getSize());
    }
}
