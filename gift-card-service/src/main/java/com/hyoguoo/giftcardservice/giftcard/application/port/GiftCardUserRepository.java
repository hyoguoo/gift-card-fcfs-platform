package com.hyoguoo.giftcardservice.giftcard.application.port;

import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.giftcard.domain.record.GiftCardUserRecord;
import java.util.Optional;
import org.springframework.data.domain.Slice;

public interface GiftCardUserRepository {

    Optional<GiftCardUser> findById(Long id);

    GiftCardUser saveOrUpdate(GiftCardUser giftCardUser);

    Slice<GiftCardUserRecord> findSliceByUserId(Long userId, Long cursor, Integer size);
}
