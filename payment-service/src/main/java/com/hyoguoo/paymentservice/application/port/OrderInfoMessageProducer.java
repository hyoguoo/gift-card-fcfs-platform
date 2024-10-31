package com.hyoguoo.paymentservice.application.port;

public interface OrderInfoMessageProducer {

    void sendOrderCompleted(Long orderInfoId);

    void sendOrderFailed(Long orderInfoId);
}
