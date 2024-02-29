package com.message_service.controller;

import com.message_service.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody String jsonMessage) {
        try {
            log.info("Received JSON message: {}", jsonMessage);
            messageService.sendToKafka(jsonMessage);
            log.info("Message sent to Kafka for processing successfully.");

            return "Message sent to Kafka for processing.";
        } catch (Exception e) {
            log.error("Error sending message to Kafka. Exception: {}", e.getMessage());
            return "Error sending message to Kafka.";
        }
}}
