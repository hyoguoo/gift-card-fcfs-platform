package com.hyoguoo.giftcardservice.giftcard.application.usecase;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.FindMyGiftCardCursorCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.result.GiftCardInfoResult;
import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardRepository;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCard;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.giftcard.domain.record.GiftCardUserRecord;
import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardLoadUseCase {

    private final GiftCardRepository giftCardRepository;
    private final GiftCardUserRepository giftCardUserRepository;

    public GiftCardInfoResult getGiftCardInfo(Long giftCardId) {
        GiftCard giftCard = giftCardRepository.findById(giftCardId)
                .orElseThrow(() -> new IllegalArgumentException("GiftCard not found"));

        return GiftCardInfoResult.builder()
                .giftCardId(giftCard.getId())
                .giftCardName(giftCard.getGiftCardName())
                .price(giftCard.getPrice())
                .saleStartAt(giftCard.getSaleStartAt())
                .build();
    }

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
