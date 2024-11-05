package com.hyoguoo.giftcardservice.giftcard.infrastructure.repository;

import com.hyoguoo.giftcardservice.giftcard.application.port.GiftCardRepository;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCard;
import com.hyoguoo.giftcardservice.giftcard.infrastructure.entity.GiftCardEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GiftCardRepositoryImpl implements GiftCardRepository {

    private final JpaGiftCardRepository jpaGiftCardRepository;

    @Override
    public Optional<GiftCard> findById(Long id) {
        return jpaGiftCardRepository.findById(id).map(GiftCardEntity::toDomain);
    }

    @Override
    public GiftCard saveOrUpdate(GiftCard giftCard) {
        return jpaGiftCardRepository.save(GiftCardEntity.from(giftCard)).toDomain();
    }

    @Override
    public void decreaseQuantity(Long id, Integer quantity) {
        jpaGiftCardRepository.decreaseQuantity(id, quantity);
    }

    @Override
    public void increaseQuantity(Long id, Integer quantity) {
        jpaGiftCardRepository.increaseQuantity(id, quantity);
    }
}
