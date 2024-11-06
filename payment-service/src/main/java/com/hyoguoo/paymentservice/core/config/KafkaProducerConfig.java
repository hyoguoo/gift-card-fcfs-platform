package com.hyoguoo.paymentservice.core.config;

import com.hyoguoo.kafka.message.GiftCardPurchaseEventMessage;
import com.hyoguoo.kafka.message.GiftCardStockDecreaseEventMessage;
import com.hyoguoo.kafka.message.GiftCardStockRollbackEventMessage;
import com.hyoguoo.kafka.message.OrderInfoCompletedMessage;
import com.hyoguoo.kafka.message.OrderInfoFailedMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.topics.gift-card-stock-decrease}")
    private String stockDecreaseTopic;

    @Value("${kafka.topics.gift-card-stock-rollback}")
    private String stockRollbackTopic;

    @Value("${kafka.topics.order-info-completed}")
    private String orderInfoCompletedTopic;

    @Value("${kafka.topics.order-info-failed}")
    private String orderInfoFailedTopic;

    @Value("${kafka.topics.gift-card-purchase}")
    private String giftCardPurchaseTopic;

    @Bean
    public KafkaTemplate<String, GiftCardStockDecreaseEventMessage> stockDecreaseKafkaTemplate(
            ProducerFactory<String, GiftCardStockDecreaseEventMessage> producerFactory) {
        KafkaTemplate<String, GiftCardStockDecreaseEventMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(stockDecreaseTopic);
        return kafkaTemplate;
    }

    @Bean
    public KafkaTemplate<String, GiftCardStockRollbackEventMessage> rollbackStockKafkaTemplate(
            ProducerFactory<String, GiftCardStockRollbackEventMessage> producerFactory) {
        KafkaTemplate<String, GiftCardStockRollbackEventMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(stockRollbackTopic);
        return kafkaTemplate;
    }

    @Bean
    public KafkaTemplate<String, OrderInfoCompletedMessage> orderInfoCompletedKafkaTemplate(
            ProducerFactory<String, OrderInfoCompletedMessage> producerFactory) {
        KafkaTemplate<String, OrderInfoCompletedMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(orderInfoCompletedTopic);
        return kafkaTemplate;
    }

    @Bean
    public KafkaTemplate<String, OrderInfoFailedMessage> orderInfoFailedKafkaTemplate(
            ProducerFactory<String, OrderInfoFailedMessage> producerFactory) {
        KafkaTemplate<String, OrderInfoFailedMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(orderInfoFailedTopic);
        return kafkaTemplate;
    }

    @Bean
    public KafkaTemplate<String, GiftCardPurchaseEventMessage> giftCardPurchaseKafkaTemplate(
            ProducerFactory<String, GiftCardPurchaseEventMessage> producerFactory) {
        KafkaTemplate<String, GiftCardPurchaseEventMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(giftCardPurchaseTopic);
        return kafkaTemplate;
    }
}
