package com.hyoguoo.giftcardservice.giftcard.application.port;

import com.hyoguoo.giftcardservice.giftcard.domain.GiftCard;
import java.util.Optional;

public interface GiftCardRepository {

    Optional<GiftCard> findById(Long id);

    GiftCard saveOrUpdate(GiftCard giftCard);

    void decreaseQuantity(Long id, Integer quantity);

    void increaseQuantity(Long id, Integer quantity);
}
