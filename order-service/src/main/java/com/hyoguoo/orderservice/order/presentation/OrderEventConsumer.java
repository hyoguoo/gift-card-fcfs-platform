package com.hyoguoo.orderservice.order.presentation;

import com.hyoguoo.kafka.message.OrderInfoCompletedMessage;
import com.hyoguoo.kafka.message.OrderInfoFailedMessage;
import com.hyoguoo.orderservice.order.presentation.port.OrderProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final OrderProcessService orderProcessService;

    @KafkaListener(topics = "${kafka.topics.order-info-completed}", groupId = "${kafka.groups.order-info-completed}")
    public void consumeOrderInfoCompletedEvent(OrderInfoCompletedMessage message) {
        log.info("Consuming order complete: {}", message.getOrderInfoId());

        orderProcessService.completedOrderInfo(message.getOrderInfoId());
    }

    @KafkaListener(topics = "${kafka.topics.order-info-failed}", groupId = "${kafka.groups.order-info-failed}")
    public void consumeOrderInfoFailedEvent(OrderInfoFailedMessage message) {
        log.info("Consuming order failed: {}", message.getOrderInfoId());

        orderProcessService.failedOrderInfo(message.getOrderInfoId());
    }
}
