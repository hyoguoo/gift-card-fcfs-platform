package com.hyoguoo.giftcardservice.giftcard.infrastructure.repository;

import com.hyoguoo.giftcardservice.giftcard.domain.record.GiftCardUserRecord;
import com.hyoguoo.giftcardservice.giftcard.infrastructure.entity.GiftCardUserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaGiftCardUserRepository extends JpaRepository<GiftCardUserEntity, Long> {


    @Query("""
            SELECT new com.hyoguoo.giftcardservice.giftcard.domain.record.GiftCardUserRecord(gc.id, g.id, g.giftCardName, gc.userGiftCardStatus, gc.purchaseDate)
            FROM GiftCardUserEntity gc
            JOIN GiftCardEntity g ON gc.giftCardId = g.id
            WHERE gc.userId = :userId
            AND gc.id < :cursor
            ORDER BY gc.id DESC
            """)
    Slice<GiftCardUserRecord> findSliceByUserId(Long userId, Long cursor, Pageable pageable);
}
