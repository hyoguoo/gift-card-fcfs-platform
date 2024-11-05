package com.hyoguoo.userservice.user.infrastructure.entity;

import com.hyoguoo.userservice.core.common.infrastructure.BaseEntity;
import com.hyoguoo.userservice.user.domain.UserGiftCard;
import com.hyoguoo.userservice.user.domain.enums.UserGiftCardStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "user_gift_card")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserGiftCardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gift_card_id", nullable = false)
    private Long giftCardId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "used_date")
    private LocalDateTime usedDate;

    @Column(name = "remaining_balance", nullable = false)
    private Long remainingBalance;

    @Column(name = "total_balance", nullable = false)
    private Long totalBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gift_card_status", nullable = false)
    private UserGiftCardStatus userGiftCardStatus;

    public static UserGiftCardEntity from(UserGiftCard userGiftCard) {
        return UserGiftCardEntity.builder()
                .id(userGiftCard.getId())
                .giftCardId(userGiftCard.getGiftCardId())
                .userId(userGiftCard.getUserId())
                .purchaseDate(userGiftCard.getPurchaseDate())
                .expirationDate(userGiftCard.getExpirationDate())
                .usedDate(userGiftCard.getUsedDate())
                .remainingBalance(userGiftCard.getRemainingBalance())
                .totalBalance(userGiftCard.getTotalBalance())
                .userGiftCardStatus(userGiftCard.getUserGiftCardStatus())
                .build();
    }

    public UserGiftCard toDomain() {
        return UserGiftCard.allArgsBuilder()
                .id(id)
                .giftCardId(giftCardId)
                .userId(userId)
                .purchaseDate(purchaseDate)
                .expirationDate(expirationDate)
                .usedDate(usedDate)
                .remainingBalance(remainingBalance)
                .totalBalance(totalBalance)
                .userGiftCardStatus(userGiftCardStatus)
                .allArgsBuild();
    }
}
