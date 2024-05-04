package com.quiikmart.notificationservice.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiikmart.notificationservice.notification.Notification;
import com.quiikmart.notificationservice.notification.handler.NotificationHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerServiceImpl implements NotificationConsumerService {
    private final Logger logger = LoggerFactory.getLogger(NotificationConsumerServiceImpl.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private NotificationHandlerFactory notificationHandlerFactory;

    @Override
    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void processMessage(String message) {
        this.logger.debug("Received message: " + message);

        try {
            Notification notification = objectMapper.readValue(message, Notification.class);
            logger.debug("Mapped notification message: {}", notification);

            var handler = notificationHandlerFactory.getHandler(notification);
            handler.handleNotification(notification);

        } catch(Exception e) {
            logger.error("Failed to parse message: {}", message, e);
        }
    }
}
