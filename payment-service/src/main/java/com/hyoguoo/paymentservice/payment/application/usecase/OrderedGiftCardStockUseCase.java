package com.hyoguoo.paymentservice.payment.application.usecase;

import com.hyoguoo.paymentservice.payment.application.port.OrderedGiftCardStockRepository;
import com.hyoguoo.paymentservice.payment.exception.PaymentOrderedStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderedGiftCardStockUseCase {

    private final OrderedGiftCardStockRepository orderedGiftCardStockRepository;

    public void decreaseStockForOrders(Long giftCardId, int quantity) throws PaymentOrderedStockException {
        orderedGiftCardStockRepository.decreaseStockForOrders(giftCardId, quantity);
    }

    public void increaseStockForOrders(Long giftCardId, int quantity) {
        orderedGiftCardStockRepository.increaseStockForOrders(giftCardId, quantity);
    }
}
