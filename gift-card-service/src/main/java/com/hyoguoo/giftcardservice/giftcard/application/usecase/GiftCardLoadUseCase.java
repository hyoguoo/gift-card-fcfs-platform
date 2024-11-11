package com.hyoguoo.giftcardservice.giftcard.application.usecase;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.FindMyGiftCardCursorCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.result.GiftCardInfoResult;
import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardRepository;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCard;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.giftcard.domain.record.GiftCardUserRecord;
import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardUserRepository;
import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardFoundException;
import com.hyoguoo.giftcardservice.giftcard.exception.GiftCardUserFoundException;
import com.hyoguoo.giftcardservice.giftcard.exception.common.GiftCardErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardLoadUseCase {

    private final GiftCardRepository giftCardRepository;
    private final GiftCardUserRepository giftCardUserRepository;

    public GiftCard getGiftCardById(Long giftCardId) {
        return giftCardRepository.findById(giftCardId)
                .orElseThrow(() -> GiftCardFoundException.of(GiftCardErrorCode.GIFT_CARD_NOT_FOUND));
    }

    public GiftCardInfoResult getGiftCardInfo(Long giftCardId) {
        GiftCard giftCard = getGiftCardById(giftCardId);

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

    public GiftCardUser getGiftCardUserById(Long giftCardUserId) {
        return giftCardUserRepository.findById(giftCardUserId)
                .orElseThrow(() -> GiftCardUserFoundException.of(GiftCardErrorCode.GIFT_CARD_USER_NOT_FOUND));
    }
}
