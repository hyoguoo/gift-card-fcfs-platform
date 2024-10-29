package com.hyoguoo.giftcardservice.application;

import com.hyoguoo.giftcardservice.application.dto.command.FindMyGiftCardCursorCommand;
import com.hyoguoo.giftcardservice.application.dto.result.MyGiftCardListResult;
import com.hyoguoo.giftcardservice.application.dto.result.MyGiftCardSummaryResult;
import com.hyoguoo.giftcardservice.application.usecase.GiftCardLoadUseCase;
import com.hyoguoo.giftcardservice.domain.record.UserGiftCardRecord;
import com.hyoguoo.giftcardservice.presentation.port.GiftCardFindService;
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
    public MyGiftCardListResult findMyGiftCardList(FindMyGiftCardCursorCommand command) {
        Slice<UserGiftCardRecord> sliceMyGiftCardWithCursor = giftCardLoadUseCase.findSliceMyGiftCardWithCursor(
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

    private Long calculateNextCursor(Slice<UserGiftCardRecord> sliceMyGiftCardWithCursor) {
        return Optional.of(sliceMyGiftCardWithCursor)
                .filter(Slice::hasNext)
                .map(Slice::getContent)
                .filter(list -> !list.isEmpty())
                .map(List::getLast)
                .map(UserGiftCardRecord::getUserGiftCardId)
                .orElse(null);
    }
}
