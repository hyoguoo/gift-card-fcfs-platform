package com.hyoguoo.giftcardservice.infrastructure.repository;

import com.hyoguoo.giftcardservice.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.domain.record.GiftCardUserRecord;
import java.util.Optional;
import org.springframework.data.domain.Slice;

public interface GiftCardUserRepository {

    Optional<GiftCardUser> findById(Long id);

    GiftCardUser saveOrUpdate(GiftCardUser giftCardUser);

    Slice<GiftCardUserRecord> findSliceByUserId(Long userId, Long cursor, Long size);
}
