package com.hyoguoo.giftcardservice.giftcard.infrastructure.repository;

import com.hyoguoo.giftcardservice.giftcard.infrastructure.entity.GiftCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaGiftCardRepository extends JpaRepository<GiftCardEntity, Long> {

    @Modifying
    @Query("update GiftCardEntity g set g.quantity = g.quantity - :quantity where g.id = :id")
    void decreaseQuantity(Long id, Integer quantity);

    @Modifying
    @Query("update GiftCardEntity g set g.quantity = g.quantity + :quantity where g.id = :id")
    void increaseQuantity(Long id, Integer quantity);
}
