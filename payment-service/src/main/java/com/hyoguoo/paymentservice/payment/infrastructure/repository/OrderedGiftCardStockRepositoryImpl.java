package com.hyoguoo.paymentservice.payment.infrastructure.repository;

import com.hyoguoo.paymentservice.payment.application.port.OrderedGiftCardStockRepository;
import com.hyoguoo.paymentservice.payment.exception.PaymentOrderedStockException;
import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderedGiftCardStockRepositoryImpl implements OrderedGiftCardStockRepository {

    private static final String GIFT_CARD_STOCK_PREFIX = "gift-card-stock:";

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void decreaseStockForOrders(Long giftCardId, int quantity) throws PaymentOrderedStockException {
        String key = GIFT_CARD_STOCK_PREFIX + giftCardId;

        Long remainingStock = stringRedisTemplate.opsForValue().increment(key, -quantity);

        if (remainingStock == null || remainingStock < 0) {
            stringRedisTemplate.opsForValue().increment(key, quantity);
            throw PaymentOrderedStockException.of(PaymentErrorCode.ORDERED_GIFT_CARD_STOCK_NOT_ENOUGH);
        }
    }

    @Override
    public void increaseStockForOrders(Long giftCardId, int quantity) {
        String key = GIFT_CARD_STOCK_PREFIX + giftCardId;
        stringRedisTemplate.opsForValue().increment(key, quantity);
    }
}
