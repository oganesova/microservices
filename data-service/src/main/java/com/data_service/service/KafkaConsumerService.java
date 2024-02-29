package com.data_service.service;

import com.data_service.dto.DataDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class KafkaConsumerService {
    private final ObjectMapper objectMapper;
    private final DataService dataService;

    public KafkaConsumerService(ObjectMapper objectMapper, DataService dataService) {
        this.objectMapper = objectMapper;
        this.dataService = dataService;
    }

    @KafkaListener(topics = "my-topic", groupId = "my-id")
    public void consumeMessage(String jsonMessage) {
        try {
            DataDto dataDto = objectMapper.readValue(jsonMessage, DataDto.class);
            log.info("Received message: {}", dataDto);
            dataService.writeToDatabase(dataDto);
            log.info("Message processed successfully.");
        } catch (Exception e) {
            log.error("Error processing message. Exception: {}", e.getMessage());
        }
    }
}

