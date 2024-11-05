package com.hyoguoo.giftcardservice.giftcard.infrastructure.entity;

import com.hyoguoo.giftcardservice.core.common.infrastructure.BaseEntity;
import com.hyoguoo.giftcardservice.giftcard.domain.GiftCard;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "gift_card")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GiftCardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gift_card_name")
    private String giftCardName;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private Long price;

    @Column(name = "total_balance")
    private Long totalBalance;

    @Column(name = "validity_days")
    private Integer validityDays;

    @Column(name = "sale_start_at")
    private LocalDateTime saleStartAt;

    public static GiftCardEntity from(GiftCard giftCard) {
        return GiftCardEntity.builder()
                .id(giftCard.getId())
                .giftCardName(giftCard.getGiftCardName())
                .quantity(giftCard.getQuantity())
                .price(giftCard.getPrice())
                .totalBalance(giftCard.getTotalBalance())
                .validityDays(giftCard.getValidityDays())
                .saleStartAt(giftCard.getSaleStartAt())
                .build();
    }

    public GiftCard toDomain() {
        return GiftCard.allArgsBuilder()
                .id(id)
                .giftCardName(giftCardName)
                .quantity(quantity)
                .price(price)
                .totalBalance(totalBalance)
                .validityDays(validityDays)
                .saleStartAt(saleStartAt)
                .allArgsBuild();
    }
}
