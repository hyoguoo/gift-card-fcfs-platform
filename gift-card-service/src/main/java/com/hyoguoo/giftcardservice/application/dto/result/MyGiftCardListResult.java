package com.hyoguoo.giftcardservice.application.dto.result;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyGiftCardListResult {

    private final List<MyGiftCardSummaryResult> myGiftCardSummaryList;
    private final Long nextCursor;
}
