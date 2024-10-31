package com.hyoguoo.paymentservice.infrastructure.producer;

import com.hyoguoo.paymentservice.application.port.OrderInfoMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FakeOrderInfoMessageProducerImpl implements OrderInfoMessageProducer {

    @Override
    public void sendOrderCompleted(Long orderInfoId) {
        // do nothing
    }

    @Override
    public void sendOrderFailed(Long orderInfoId) {
        // do nothing
    }
}
