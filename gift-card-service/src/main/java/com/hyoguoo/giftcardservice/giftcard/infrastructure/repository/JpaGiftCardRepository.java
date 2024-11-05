package com.hyoguoo.giftcardservice.giftcard.infrastructure.repository;

import com.hyoguoo.giftcardservice.giftcard.infrastructure.entity.GiftCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGiftCardRepository extends JpaRepository<GiftCardEntity, Long> {

}
