package com.hyoguoo.giftcardservice.giftcard.infrastructure.entity;

import com.hyoguoo.giftcardservice.core.common.infrastructure.BaseEntity;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCardUser;
import com.hyoguoo.giftcardservice.giftcard.domain.enums.UserGiftCardStatus;
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
@Table(name = "gift_card_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GiftCardUserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gift_card_id", nullable = false)
    private Long giftCardId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "used_date")
    private LocalDateTime usedDate;

    @Column(name = "remaining_balance")
    private Long remainingBalance;

    @Column(name = "total_balance")
    private Long totalBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gift_card_status")
    private UserGiftCardStatus userGiftCardStatus;

    public static GiftCardUserEntity from(GiftCardUser giftCardUser) {
        return GiftCardUserEntity.builder()
                .id(giftCardUser.getId())
                .giftCardId(giftCardUser.getGiftCardId())
                .userId(giftCardUser.getUserId())
                .purchaseDate(giftCardUser.getPurchaseDate())
                .expirationDate(giftCardUser.getExpirationDate())
                .usedDate(giftCardUser.getUsedDate())
                .remainingBalance(giftCardUser.getRemainingBalance())
                .totalBalance(giftCardUser.getTotalBalance())
                .userGiftCardStatus(giftCardUser.getUserGiftCardStatus())
                .build();
    }

    public GiftCardUser toDomain() {
        return GiftCardUser.allArgsBuilder()
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
