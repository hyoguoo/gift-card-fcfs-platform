package com.hyoguoo.paymentservice.payment.application.port;

public interface OrderInfoMessageProducer {

    void sendOrderCompleted(Long orderInfoId);

    void sendOrderFailed(Long orderInfoId);
}
