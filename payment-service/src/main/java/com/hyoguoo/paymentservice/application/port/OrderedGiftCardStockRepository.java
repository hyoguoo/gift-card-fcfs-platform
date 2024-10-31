package com.hyoguoo.paymentservice.application.port;

import com.hyoguoo.paymentservice.exception.PaymentOrderedStockException;

public interface OrderedGiftCardStockRepository {

    void decreaseStockForOrders(Long giftCardId, int quantity) throws PaymentOrderedStockException;

    void increaseStockForOrders(Long giftCardId, int quantity);
}
