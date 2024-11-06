package com.hyoguoo.giftcardservice.core.config;

import com.hyoguoo.kafka.message.GiftCardUserEventMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.topics.gift-card-user-sync}")
    private String giftCardUserSyncTopic;

    @Bean
    public KafkaTemplate<String, GiftCardUserEventMessage> giftCardUserDataSyncKafkaTemplate(
            ProducerFactory<String, GiftCardUserEventMessage> producerFactory) {
        KafkaTemplate<String, GiftCardUserEventMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(giftCardUserSyncTopic);
        return kafkaTemplate;
    }
}
