package com.hyoguoo.orderservice.config;

import com.hyoguoo.kafka.message.CouponStockDecreaseEventMessage;
import com.hyoguoo.kafka.message.CouponStockRollbackEventMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.topics.coupon-stock-decrease}")
    private String stockDecreaseTopic;

    @Value("${kafka.topics.coupon-stock-rollback}")
    private String stockRollbackTopic;

    @Bean
    public KafkaTemplate<String, CouponStockDecreaseEventMessage> stockDecreaseKafkaTemplate(
            ProducerFactory<String, CouponStockDecreaseEventMessage> producerFactory) {
        KafkaTemplate<String, CouponStockDecreaseEventMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(stockDecreaseTopic);
        return kafkaTemplate;
    }

    @Bean
    public KafkaTemplate<String, CouponStockRollbackEventMessage> rollbackStockKafkaTemplate(
            ProducerFactory<String, CouponStockRollbackEventMessage> producerFactory) {
        KafkaTemplate<String, CouponStockRollbackEventMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(stockRollbackTopic);
        return kafkaTemplate;
    }
}
