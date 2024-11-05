package com.hyoguoo.paymentservice.payment.application.port;

import com.hyoguoo.paymentservice.payment.exception.PaymentOrderedStockException;

public interface OrderedGiftCardStockRepository {

    void decreaseStockForOrders(Long giftCardId, int quantity) throws PaymentOrderedStockException;

    void increaseStockForOrders(Long giftCardId, int quantity);
}
