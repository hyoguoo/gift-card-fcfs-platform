package com.hyoguoo.paymentservice.payment.infrastructure.producer;

import com.hyoguoo.kafka.message.OrderInfoCompletedMessage;
import com.hyoguoo.kafka.message.OrderInfoFailedMessage;
import com.hyoguoo.paymentservice.payment.application.port.OrderInfoMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderInfoMessageProducerImpl implements OrderInfoMessageProducer {

    private final KafkaTemplate<String, OrderInfoCompletedMessage> orderInfoCompletedKafkaTemplate;
    private final KafkaTemplate<String, OrderInfoFailedMessage> orderInfoFailedKafkaTemplate;

    @Override
    public void sendOrderCompleted(Long orderInfoId) {
        OrderInfoCompletedMessage message = OrderInfoCompletedMessage.builder()
                .orderInfoId(orderInfoId)
                .build();
        orderInfoCompletedKafkaTemplate.sendDefault(message);
    }

    @Override
    public void sendOrderFailed(Long orderInfoId) {
        OrderInfoFailedMessage message = OrderInfoFailedMessage.builder()
                .orderInfoId(orderInfoId)
                .build();
        orderInfoFailedKafkaTemplate.sendDefault(message);
    }
}
