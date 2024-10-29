package com.hyoguoo.giftcardservice.application.port;

import com.hyoguoo.giftcardservice.domain.GiftCard;
import java.util.Optional;

public interface GiftCardRepository {

    Optional<GiftCard> findById(Long id);

    GiftCard saveOrUpdate(GiftCard giftCard);
}
