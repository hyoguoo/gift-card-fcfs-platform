package com.hyoguoo.orderservice.core.config;

import com.hyoguoo.kafka.message.GiftCardStockDecreaseEventMessage;
import com.hyoguoo.kafka.message.GiftCardStockRollbackEventMessage;
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
}
