package com.hyoguoo.paymentservice.payment.infrastructure.repository;

import com.hyoguoo.paymentservice.payment.application.port.OrderedGiftCardStockRepository;
import com.hyoguoo.paymentservice.payment.exception.PaymentOrderedStockException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderedGiftCardStockRepositoryImpl implements OrderedGiftCardStockRepository {

    private static final String GIFT_CARD_STOCK_PREFIX = "gift-card-stock:";

    private final DefaultRedisScript<Long> decreaseStockScript;
    private final DefaultRedisScript<Long> increaseStockScript;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void decreaseStockForOrders(Long giftCardId, int quantity) throws PaymentOrderedStockException {
        Long stock = stringRedisTemplate.execute(decreaseStockScript,
                List.of(GIFT_CARD_STOCK_PREFIX + giftCardId), String.valueOf(quantity));
        if (stock == null || stock == -1) {
            throw new PaymentOrderedStockException();
        }
    }

    @Override
    public void increaseStockForOrders(Long giftCardId, int quantity) {
        stringRedisTemplate.execute(increaseStockScript,
                List.of(GIFT_CARD_STOCK_PREFIX + giftCardId), String.valueOf(quantity));
    }
}
