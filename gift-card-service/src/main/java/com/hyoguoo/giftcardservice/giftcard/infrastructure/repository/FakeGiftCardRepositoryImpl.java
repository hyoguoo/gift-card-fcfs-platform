package com.hyoguoo.giftcardservice.giftcard.infrastructure.repository;

import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardRepository;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCard;
import com.hyoguoo.util.ReflectionUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class FakeGiftCardRepositoryImpl implements GiftCardRepository {

    private final Map<Long, GiftCard> giftCardMap = new HashMap<>();
    private Long id = 0L;

    @Override
    public Optional<GiftCard> findById(Long id) {
        return Optional.ofNullable(giftCardMap.get(id));
    }

    @Override
    public GiftCard saveOrUpdate(GiftCard giftCard) {
        if (giftCard.getId() == null) {
            ReflectionUtil.setField(giftCard, "id", ++id);
        }
        return giftCardMap.put(giftCard.getId(), giftCard);
    }
}
