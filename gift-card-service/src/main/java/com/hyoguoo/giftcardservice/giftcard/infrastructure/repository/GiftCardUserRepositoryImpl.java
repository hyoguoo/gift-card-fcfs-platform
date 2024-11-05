package com.hyoguoo.giftcardservice.giftcard.infrastructure.repository;

import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardUserRepository;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.giftcard.domain.record.GiftCardUserRecord;
import com.hyoguoo.giftcardservice.giftcard.infrastructure.entity.GiftCardUserEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GiftCardUserRepositoryImpl implements GiftCardUserRepository {

    private final JpaGiftCardUserRepository jpaGiftCardUserRepository;

    @Override
    public Optional<GiftCardUser> findById(Long id) {
        return jpaGiftCardUserRepository.findById(id).map(GiftCardUserEntity::toDomain);
    }

    @Override
    public GiftCardUser saveOrUpdate(GiftCardUser giftCardUser) {
        return jpaGiftCardUserRepository.save(GiftCardUserEntity.from(giftCardUser)).toDomain();
    }

    @Override
    public Slice<GiftCardUserRecord> findSliceByUserId(Long userId, Long cursor, Integer size) {
        return jpaGiftCardUserRepository.findSliceByUserId(userId, cursor, Pageable.ofSize(size));
    }
}
