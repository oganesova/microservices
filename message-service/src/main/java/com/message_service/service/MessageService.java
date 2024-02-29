package com.message_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToKafka(String jsonMessage) {
        try {
            log.info("Sending message to Kafka: {}", jsonMessage);
            kafkaTemplate.send("my-topic", jsonMessage);
            log.info("Message sent to Kafka successfully.");
        } catch (Exception e) {
            log.error("Error sending message to Kafka. Exception: {}", e.getMessage());
        }
    }
}