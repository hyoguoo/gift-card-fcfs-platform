package com.hyoguoo.userservice.user.presentation.port;

import com.hyoguoo.userservice.user.application.dto.command.UserGiftCardUpsertCommand;

public interface UserGiftCardSyncService {

    void upsertUserGiftCard(UserGiftCardUpsertCommand command);
}
