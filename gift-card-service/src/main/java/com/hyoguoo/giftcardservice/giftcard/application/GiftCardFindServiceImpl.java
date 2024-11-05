package com.hyoguoo.giftcardservice.giftcard.application;

import com.hyoguoo.giftcardservice.giftcard.application.dto.command.FindMyGiftCardCursorCommand;
import com.hyoguoo.giftcardservice.giftcard.application.dto.result.GiftCardInfoResult;
import com.hyoguoo.giftcardservice.giftcard.application.dto.result.MyGiftCardListResult;
import com.hyoguoo.giftcardservice.giftcard.application.dto.result.MyGiftCardSummaryResult;
import com.hyoguoo.giftcardservice.giftcard.application.usecase.GiftCardLoadUseCase;
import com.hyoguoo.giftcardservice.giftcard.domain.record.GiftCardUserRecord;
import com.hyoguoo.giftcardservice.giftcard.presentation.port.GiftCardFindService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftCardFindServiceImpl implements GiftCardFindService {

    private final GiftCardLoadUseCase giftCardLoadUseCase;

    @Override
    public GiftCardInfoResult getGiftCardInfo(Long giftCardId) {
        return giftCardLoadUseCase.getGiftCardInfo(giftCardId);
    }

    @Override
    public MyGiftCardListResult findMyGiftCardList(FindMyGiftCardCursorCommand command) {
        Slice<GiftCardUserRecord> sliceMyGiftCardWithCursor = giftCardLoadUseCase.findSliceMyGiftCardWithCursor(
                command);

        List<MyGiftCardSummaryResult> myGiftCardSummaryList = sliceMyGiftCardWithCursor.getContent()
                .stream()
                .map(MyGiftCardSummaryResult::from)
                .toList();

        Long nextCursor = calculateNextCursor(sliceMyGiftCardWithCursor);

        return MyGiftCardListResult.builder()
                .myGiftCardSummaryList(myGiftCardSummaryList)
                .nextCursor(nextCursor)
                .build();
    }

    private Long calculateNextCursor(Slice<GiftCardUserRecord> sliceMyGiftCardWithCursor) {
        return Optional.of(sliceMyGiftCardWithCursor)
                .filter(Slice::hasNext)
                .map(Slice::getContent)
                .filter(list -> !list.isEmpty())
                .map(List::getLast)
                .map(GiftCardUserRecord::getUserGiftCardId)
                .orElse(null);
    }
}
