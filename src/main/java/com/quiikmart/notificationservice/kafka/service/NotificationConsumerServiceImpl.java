package com.quiikmart.notificationservice.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiikmart.notificationservice.dto.NotificationRequestDto;
import com.quiikmart.notificationservice.notification.handler.NotificationHandlerFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerServiceImpl implements NotificationConsumerService {
    private final Logger logger = LoggerFactory.getLogger(NotificationConsumerServiceImpl.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private NotificationHandlerFactory notificationHandlerFactory;

    @Override
    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void processMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        String message = record.value();
        this.logger.debug("Received message: " + message);
        try {
            NotificationRequestDto notificationRequest = objectMapper.readValue(message, NotificationRequestDto.class);
            logger.debug("Notification request: {}", notificationRequest);

            var handler = notificationHandlerFactory.getHandler(notificationRequest);
            handler.handleNotification(notificationRequest);

            acknowledgment.acknowledge();
        } catch(Exception e) {
            logger.error("Failed to parse message: {}", message, e);
        }
    }
}
