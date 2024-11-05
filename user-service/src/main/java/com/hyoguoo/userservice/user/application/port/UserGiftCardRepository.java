package com.hyoguoo.userservice.user.application.port;

import com.hyoguoo.userservice.user.domain.UserGiftCard;

public interface UserGiftCardRepository {

    UserGiftCard saveOrUpdate(UserGiftCard userGiftCard);
}
