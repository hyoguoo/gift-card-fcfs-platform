package com.hyoguoo.userservice.user.application;

import com.hyoguoo.userservice.user.application.dto.command.UserGiftCardUpsertCommand;
import com.hyoguoo.userservice.user.application.usecase.UserGiftCardSyncUseCase;
import com.hyoguoo.userservice.user.presentation.port.UserGiftCardSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGiftCardSyncServiceImpl implements UserGiftCardSyncService {

    private final UserGiftCardSyncUseCase userGiftCardSyncUseCase;

    @Override
    public void upsertUserGiftCard(UserGiftCardUpsertCommand command) {
        userGiftCardSyncUseCase.upsertUserGiftCard(command);
    }
}
