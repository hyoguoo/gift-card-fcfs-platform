package com.hyoguoo.paymentservice.payment.application.usecase;

import com.hyoguoo.paymentservice.payment.application.port.OrderedGiftCardStockMessageProducer;
import com.hyoguoo.paymentservice.payment.application.port.OrderedGiftCardStockRepository;
import com.hyoguoo.paymentservice.payment.exception.PaymentOrderedStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderedGiftCardStockUseCase {

    private final OrderedGiftCardStockRepository orderedGiftCardStockRepository;
    private final OrderedGiftCardStockMessageProducer orderedGiftCardStockMessageProducer;

    public void decreaseStockForOrders(Long giftCardId, Integer quantity) throws PaymentOrderedStockException {
        orderedGiftCardStockMessageProducer.sendGiftCardStockDecreaseEventMessage(giftCardId, quantity);
        orderedGiftCardStockRepository.decreaseStockForOrders(giftCardId, quantity);
    }

    public void increaseStockForOrders(Long giftCardId, Integer quantity) {
        orderedGiftCardStockRepository.increaseStockForOrders(giftCardId, quantity);
        orderedGiftCardStockMessageProducer.sendGiftCardStockRollbackEventMessage(giftCardId, quantity);
    }
}
