package com.hyoguoo.userservice.user.application.usecase;

import com.hyoguoo.userservice.user.application.dto.command.UserGiftCardUpsertCommand;
import com.hyoguoo.userservice.user.application.port.UserGiftCardRepository;
import com.hyoguoo.userservice.user.domain.UserGiftCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGiftCardSyncUseCase {

    private final UserGiftCardRepository userGiftCardRepository;

    public void upsertUserGiftCard(UserGiftCardUpsertCommand command) {
        UserGiftCard userGiftCard = UserGiftCard.allArgsBuilder()
                .id(command.getId())
                .userId(command.getUserId())
                .giftCardId(command.getGiftCardId())
                .purchaseDate(command.getPurchaseDate())
                .expirationDate(command.getExpirationDate())
                .usedDate(command.getUsedDate())
                .remainingBalance(command.getRemainingBalance())
                .totalBalance(command.getTotalBalance())
                .userGiftCardStatus(command.getUserGiftCardStatus())
                .allArgsBuild();

        userGiftCardRepository.saveOrUpdate(userGiftCard);
    }
}
