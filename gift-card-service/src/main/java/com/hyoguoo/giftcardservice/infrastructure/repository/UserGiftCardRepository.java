package com.hyoguoo.giftcardservice.infrastructure.repository;

import com.hyoguoo.giftcardservice.domain.UserGiftCard;
import com.hyoguoo.giftcardservice.domain.record.UserGiftCardRecord;
import java.util.Optional;
import org.springframework.data.domain.Slice;

public interface UserGiftCardRepository {

    Optional<UserGiftCard> findById(Long id);

    UserGiftCard saveOrUpdate(UserGiftCard userGiftCard);

    Slice<UserGiftCardRecord> findSliceByUserId(Long userId, Long cursor, Long size);
}
