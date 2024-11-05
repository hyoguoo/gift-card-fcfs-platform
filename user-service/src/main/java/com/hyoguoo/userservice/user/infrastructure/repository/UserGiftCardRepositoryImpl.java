package com.hyoguoo.userservice.user.infrastructure.repository;

import com.hyoguoo.userservice.user.application.port.UserGiftCardRepository;
import com.hyoguoo.userservice.user.domain.UserGiftCard;
import com.hyoguoo.userservice.user.infrastructure.entity.UserGiftCardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserGiftCardRepositoryImpl implements UserGiftCardRepository {

    private final JpaUserGiftCardRepository jpaUserGiftCardRepository;

    @Override
    public UserGiftCard saveOrUpdate(UserGiftCard userGiftCard) {
        return jpaUserGiftCardRepository.save(UserGiftCardEntity.from(userGiftCard)).toDomain();
    }
}
