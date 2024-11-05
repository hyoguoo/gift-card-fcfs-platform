package com.hyoguoo.paymentservice.payment.infrastructure.repository;

import com.hyoguoo.paymentservice.payment.application.port.OrderedGiftCardStockRepository;
import com.hyoguoo.paymentservice.payment.exception.PaymentOrderedStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FakeOrderedGiftCardStockRepositoryImpl implements OrderedGiftCardStockRepository {

    private Integer stock = 100;

    @Override
    public void decreaseStockForOrders(Long giftCardId, int quantity) throws PaymentOrderedStockException {
        if (stock - quantity < 0) {
            throw new PaymentOrderedStockException();
        }

        stock -= quantity;
    }

    @Override
    public void increaseStockForOrders(Long giftCardId, int quantity) {
        stock += quantity;
    }
}