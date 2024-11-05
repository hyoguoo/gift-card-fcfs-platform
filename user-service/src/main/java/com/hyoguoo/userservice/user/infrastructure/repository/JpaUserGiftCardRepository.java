package com.hyoguoo.userservice.user.infrastructure.repository;

import com.hyoguoo.userservice.user.infrastructure.entity.UserGiftCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserGiftCardRepository extends JpaRepository<UserGiftCardEntity, Long> {

}
